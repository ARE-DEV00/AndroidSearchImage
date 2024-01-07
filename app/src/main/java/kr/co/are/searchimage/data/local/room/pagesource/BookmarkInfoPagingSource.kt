package kr.co.are.searchimage.data.local.room.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity

class BookmarkInfoPagingSource(private val appDatabase: AppDatabase, private val perPage: Int) :
    PagingSource<Int, PhotoDetailEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDetailEntity> {
        try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val photoDetailEntityList = withContext(Dispatchers.IO) {
                appDatabase.bookmarkInfoDao()
                    .selectBookmarkInfoPagingList(page, perPage).map {
                        convertPhotoDetailEntity(it)
                    }
            }

            return LoadResult.Page(
                data = photoDetailEntityList,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (photoDetailEntityList.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private fun convertPhotoDetailEntity(tableBookmarkInfoEntity: TableBookmarkInfoEntity): PhotoDetailEntity {
        return PhotoDetailEntity(
            imageInfo = PhotoDetailEntity.ImageInfo(
                id = tableBookmarkInfoEntity.id,
                author = tableBookmarkInfoEntity.author,
                width = tableBookmarkInfoEntity.width,
                height = tableBookmarkInfoEntity.height,
                createdAt = tableBookmarkInfoEntity.createdAt,
                description = tableBookmarkInfoEntity.description,
                isBookmark = true
            ),
            imageUrl = PhotoDetailEntity.ImageUrl(
                raw = tableBookmarkInfoEntity.imageUrlRaw,
                full = tableBookmarkInfoEntity.imageUrlFull,
                regular = tableBookmarkInfoEntity.imageUrlRegular,
                small = tableBookmarkInfoEntity.imageUrlSmall,
                thumb = tableBookmarkInfoEntity.imageUrlThumb
            )
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoDetailEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.takeIf { it >= STARTING_PAGE_INDEX }
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}