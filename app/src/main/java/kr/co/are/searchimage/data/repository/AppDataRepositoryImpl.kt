package kr.co.are.searchimage.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity
import kr.co.are.searchimage.data.local.room.pagesource.BookmarkInfoPagingSource
import kr.co.are.searchimage.data.remote.api.model.exception.ErrorResponse
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.data.remote.api.model.response.PhotoDetailResponse
import kr.co.are.searchimage.data.remote.api.pagingsoruce.PhotoDetailPagingSource
import kr.co.are.searchimage.data.remote.api.pagingsoruce.SearchPhotoDetailPagingSource
import kr.co.are.searchimage.data.remote.utils.ApiExceptionUtil
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity.*
import kr.co.are.searchimage.domain.entity.SearchPhotoListEntity
import kr.co.are.searchimage.domain.enums.ExceptionCodeStatus
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import okhttp3.ResponseBody
import retrofit2.Retrofit
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class AppDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val retrofit: Retrofit,
    private val appDatabase: AppDatabase,
) : AppDataRepository {

    override suspend fun getPhotoList(
        page: Int,
        perPage: Int,
    ): Flow<List<PhotoDetailEntity>> {
        return flow {
            try {
                val response = apiService.getPhotoList(page, perPage)
                if (response.isSuccessful) {
                    val photoDetailResponseList = response.body()
                    val photoDetailEntityList =
                        photoDetailResponseList?.map { photoDetailResponse ->
                            convertPhotoDetailEntity(photoDetailResponse)
                        } ?: emptyList()
                    emit(photoDetailEntityList)
                } else {
                    Timber.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Timber.e(uhe)
                throw ApiExceptionUtil.apiException(
                    ErrorResponse(
                        code = ExceptionCodeStatus.InnerNoConnectivityException.code,
                        message = ExceptionCodeStatus.InnerNoConnectivityException.name
                    )
                )
            } catch (t: Throwable) {
                t.printStackTrace()
                throw t

            }
        }
    }

    override suspend fun getPhotoPagingList(
        perPage: Int
    ): Flow<Pager<Int, PhotoDetailEntity>> {
        return flow {
            emit(
                Pager(config = PagingConfig(
                    pageSize = 20, enablePlaceholders = true
                ), pagingSourceFactory = {
                    PhotoDetailPagingSource(
                        apiService = apiService,
                        appDatabase = appDatabase,
                        perPage = perPage
                    )
                })
            )
        }
    }

    private var searchPhotoDetailPagingSource: SearchPhotoDetailPagingSource? = null
    override suspend fun getSearchPhotoPagingList(
        query: String,
        perPage: Int,
        orderBy: String
    ): Flow<Pager<Int, PhotoDetailEntity>> {
        return flow {
            Timber.e("#### https://api.unsplash.com/search/photos?query=${query}")

            searchPhotoDetailPagingSource?.invalidate()
            if (searchPhotoDetailPagingSource == null) {
                searchPhotoDetailPagingSource = SearchPhotoDetailPagingSource(
                    apiService = apiService,
                    appDatabase = appDatabase,
                    query = query,
                    perPage = perPage,
                    orderBy = orderBy
                )
            } else if (searchPhotoDetailPagingSource?.invalid == true) {
                searchPhotoDetailPagingSource = SearchPhotoDetailPagingSource(
                    apiService = apiService,
                    appDatabase = appDatabase,
                    query = query,
                    perPage = perPage,
                    orderBy = orderBy
                )
            }

            emit(
                Pager(config = PagingConfig(
                    pageSize = 20, enablePlaceholders = true,
                ), pagingSourceFactory = {
                    searchPhotoDetailPagingSource!!
                })

            )
        }
    }

    override suspend fun getPhotoDetail(id: String): Flow<PhotoDetailEntity> {
        return flow {
            try {
                val response = apiService.getPhotoDetail(id)
                if (response.isSuccessful) {
                    val photoDetailResponse = response.body()

                    if (photoDetailResponse != null) {
                        emit(
                            convertPhotoDetailEntity(photoDetailResponse)
                        )
                    }

                } else {
                    Timber.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Timber.e(uhe)
                throw ApiExceptionUtil.apiException(
                    ErrorResponse(
                        code = ExceptionCodeStatus.InnerNoConnectivityException.code,
                        message = ExceptionCodeStatus.InnerNoConnectivityException.name
                    )
                )
            } catch (t: Throwable) {
                t.printStackTrace()
                throw t

            }
        }
    }

    override suspend fun getSearchPhotoList(
        query: String, page: Int, perPage: Int, orderBy: String
    ): Flow<SearchPhotoListEntity> {
        return flow {
            try {
                val response = apiService.searchPhotoList(query, page, perPage, orderBy)
                if (response.isSuccessful) {
                    val searchPhotoList = response.body()
                    val photoDetailEntityList =
                        searchPhotoList?.results?.map { photoDetailResponse ->
                            convertPhotoDetailEntity(photoDetailResponse)
                        } ?: emptyList()
                    emit(
                        SearchPhotoListEntity(
                            total = searchPhotoList?.total ?: 0,
                            totalPages = searchPhotoList?.totalPages ?: 0,
                            list = photoDetailEntityList,
                        )
                    )
                } else {
                    Timber.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Timber.e(uhe, uhe.message ?: "")
                throw ApiExceptionUtil.apiException(
                    ErrorResponse(
                        code = ExceptionCodeStatus.InnerNoConnectivityException.code,
                        message = ExceptionCodeStatus.InnerNoConnectivityException.name
                    )
                )
            } catch (t: Throwable) {
                t.printStackTrace()
                throw t

            }
        }
    }


    override suspend fun addBookmarkInfo(
        id: String,
        author: String,
        width: Int,
        height: Int,
        createdAt: String,
        description: String,
        imageUrlRaw: String,
        imageUrlFull: String,
        imageUrlRegular: String,
        imageUrlSmall: String,
        imageUrlThumb: String
    ): Flow<Boolean> {
        return flow {
            var tableBookmarkInfoEntity = appDatabase.bookmarkInfoDao().selectBookmarkInfoById(id)

            if (tableBookmarkInfoEntity != null) {
                appDatabase.bookmarkInfoDao().update(tableBookmarkInfoEntity)
            } else {
                tableBookmarkInfoEntity = TableBookmarkInfoEntity(
                    id = id,
                    author = author,
                    width = width,
                    height = height,
                    createdAt = createdAt,
                    description = description,
                    imageUrlRaw = imageUrlRaw,
                    imageUrlFull = imageUrlFull,
                    imageUrlRegular = imageUrlRegular,
                    imageUrlSmall = imageUrlSmall,
                    imageUrlThumb = imageUrlThumb
                )
                appDatabase.bookmarkInfoDao().insert(tableBookmarkInfoEntity)
            }
            emit(true)
        }
    }

    override suspend fun deleteBookmarkInfo(id: String): Flow<Boolean> {
        return flow {
            appDatabase.bookmarkInfoDao().deleteBookmarkInfo(id = id)
            emit(true)
        }
    }

    override suspend fun getBookmarkInfo(id: String): Flow<PhotoDetailEntity?> {
        return flow {
            val tableBookmarkInfoEntity =
                appDatabase.bookmarkInfoDao().selectBookmarkInfoById(id = id)
            if (tableBookmarkInfoEntity != null) {
                emit(
                    convertPhotoDetailEntity(tableBookmarkInfoEntity)
                )
            } else {
                emit(null)
            }
        }
    }

    override suspend fun getBookmarkInfoList(): Flow<List<PhotoDetailEntity>> {
        return flow {
            val tableBookmarkInfoEntity = appDatabase.bookmarkInfoDao().selectBookmarkInfoList()
            if (tableBookmarkInfoEntity != null) {
                emit(tableBookmarkInfoEntity.map {
                    convertPhotoDetailEntity(it)
                })
            } else {
                emit(emptyList())
            }
        }
    }

    override suspend fun getBookmarkInfoPagingList(perPage: Int): Flow<Pager<Int, PhotoDetailEntity>> {
        Timber.d("#### AppDatabaseRepositoryImpl-getBookmarkInfoPagingList")
        return flow {
            Timber.d("#### AppDatabaseRepositoryImpl-getBookmarkInfoPagingList-2")
            emit(Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true,
                    maxSize = 50
                ),
                pagingSourceFactory = {
                    BookmarkInfoPagingSource(appDatabase, perPage)
                }
            ))
        }
    }

    private fun convertPhotoDetailEntity(tableBookmarkInfoEntity: TableBookmarkInfoEntity): PhotoDetailEntity {
        return PhotoDetailEntity(
            imageUrl = PhotoDetailEntity.ImageUrl(
                raw = tableBookmarkInfoEntity.imageUrlRaw,
                full = tableBookmarkInfoEntity.imageUrlFull,
                regular = tableBookmarkInfoEntity.imageUrlRegular,
                small = tableBookmarkInfoEntity.imageUrlSmall,
                thumb = tableBookmarkInfoEntity.imageUrlThumb
            ),
            imageInfo = PhotoDetailEntity.ImageInfo(
                id = tableBookmarkInfoEntity.id,
                author = tableBookmarkInfoEntity.author,
                width = tableBookmarkInfoEntity.width,
                height = tableBookmarkInfoEntity.height,
                createdAt = tableBookmarkInfoEntity.createdAt,
                description = tableBookmarkInfoEntity.description,
            )
        )
    }

    private fun convertPhotoDetailEntity(photoDetailResponse: PhotoDetailResponse): PhotoDetailEntity {
        return PhotoDetailEntity(
            imageInfo = ImageInfo(
                id = photoDetailResponse.id,
                author = photoDetailResponse.user?.name ?: "",
                width = photoDetailResponse.width ?: -1,
                height = photoDetailResponse.height ?: -1,
                createdAt = photoDetailResponse.createdAt ?: "",
                description = photoDetailResponse.description ?: ""
            ),
            imageUrl = ImageUrl(
                raw = photoDetailResponse.urls?.raw ?: "",
                full = photoDetailResponse.urls?.full ?: "",
                regular = photoDetailResponse.urls?.regular ?: "",
                small = photoDetailResponse.urls?.small ?: "",
                thumb = photoDetailResponse.urls?.thumb ?: "",
            ),
        )
    }

    private fun errorConverter(errorBody: ResponseBody?): ErrorResponse? {
        return errorBody?.let {
            retrofit.responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java, ErrorResponse::class.java.annotations
            ).convert(it)
        }
    }
}