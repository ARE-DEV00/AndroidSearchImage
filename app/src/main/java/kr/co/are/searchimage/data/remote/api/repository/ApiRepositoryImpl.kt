package kr.co.are.searchimage.data.remote.api.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.are.searchimage.data.remote.api.model.exception.ErrorResponse
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.data.remote.api.model.response.PhotoDetailResponse
import kr.co.are.searchimage.data.remote.api.pagingsoruce.PhotoDetailPagingSource
import kr.co.are.searchimage.data.remote.api.pagingsoruce.SearchPhotoDetailPagingSource
import kr.co.are.searchimage.data.remote.utils.ApiExceptionUtil
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity.*
import kr.co.are.searchimage.domain.entitiy.SearchPhotoListEntity
import kr.co.are.searchimage.domain.enums.ExceptionCodeStatus
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService, private val retrofit: Retrofit
) : ApiRepository {

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
                    Logger.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Logger.e(uhe, uhe.message ?: "")
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
                        apiService = apiService, perPage
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
            Logger.e("#### https://api.unsplash.com/search/photos?query=${query}")

            searchPhotoDetailPagingSource?.invalidate()
            if (searchPhotoDetailPagingSource == null) {
                searchPhotoDetailPagingSource = SearchPhotoDetailPagingSource(
                    apiService = apiService,
                    query = query,
                    perPage = perPage,
                    orderBy = orderBy
                )
            } else if (searchPhotoDetailPagingSource?.invalid == true) {
                searchPhotoDetailPagingSource = SearchPhotoDetailPagingSource(
                    apiService = apiService,
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
                    Logger.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Logger.e(uhe, uhe.message ?: "")
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
                    Logger.d("#### response: ${response.errorBody().toString()}")
                    throw ApiExceptionUtil.apiException(errorConverter(response.errorBody()))
                }
            } catch (uhe: UnknownHostException) {
                Logger.e(uhe, uhe.message ?: "")
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