package kr.co.are.searchimage.data.remote.api.pagingsoruce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity

class PhotoDetailPagingSource(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val perPage: Int,
) :
    PagingSource<Int, PhotoDetailEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDetailEntity> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getPhotoList(page, perPage)
            val photoDetailResponseList = response.body() ?: emptyList()

            val photoDetailEntityList =
                photoDetailResponseList.map { photoDetailResponse ->
                    val isBookmark = withContext(Dispatchers.IO) {
                        val selectBookmarkInfoById = appDatabase.bookmarkInfoDao()
                            .selectBookmarkInfoById(id = photoDetailResponse.id)
                        selectBookmarkInfoById != null
                    }
                    PhotoDetailEntity(
                        imageInfo = PhotoDetailEntity.ImageInfo(
                            id = photoDetailResponse.id,
                            author = photoDetailResponse.user?.name ?: "",
                            width = photoDetailResponse.width ?: -1,
                            height = photoDetailResponse.height ?: -1,
                            createdAt = photoDetailResponse.createdAt ?: "",
                            description = photoDetailResponse.description ?: "",
                            isBookmark = isBookmark
                        ),
                        imageUrl = PhotoDetailEntity.ImageUrl(
                            raw = photoDetailResponse.urls?.raw ?: "",
                            full = photoDetailResponse.urls?.full ?: "",
                            regular = photoDetailResponse.urls?.regular ?: "",
                            small = photoDetailResponse.urls?.small ?: "",
                            thumb = photoDetailResponse.urls?.thumb ?: "",
                        )
                    )
                }

            LoadResult.Page(
                data = photoDetailEntityList,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (photoDetailResponseList.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, PhotoDetailEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}