package kr.co.are.searchimage.data.remote.api.repository


import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.are.searchimage.data.model.exception.ErrorResponse
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.data.remote.utils.ApiExceptionUtil
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.entitiy.SearchPhotoListEntity
import kr.co.are.searchimage.domain.enums.ExceptionCodeStatus
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val retrofit: Retrofit
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
                            PhotoDetailEntity(
                                id = photoDetailResponse.id,
                                raw = photoDetailResponse.urls?.raw,
                                full = photoDetailResponse.urls?.full,
                                regular = photoDetailResponse.urls?.regular,
                                small = photoDetailResponse.urls?.small,
                                thumb = photoDetailResponse.urls?.thumb,
                            )
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

    override suspend fun getPhotoDetail(id: String): Flow<PhotoDetailEntity> {
        return flow {
            try {
                val response = apiService.getPhotoDetail(id)
                if (response.isSuccessful) {
                    val photoDetailResponse = response.body()

                    if (photoDetailResponse != null) {
                        emit(
                            PhotoDetailEntity(
                                id = photoDetailResponse.id,
                                raw = photoDetailResponse.urls?.raw,
                                full = photoDetailResponse.urls?.full,
                                regular = photoDetailResponse.urls?.regular,
                                small = photoDetailResponse.urls?.small,
                                thumb = photoDetailResponse.urls?.thumb,
                            )
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

    override suspend fun searchPhotoList(
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): Flow<SearchPhotoListEntity> {
        return flow {
            try {
                val response = apiService.searchPhotoList(query, page, perPage, orderBy)
                if (response.isSuccessful) {
                    val searchPhotoList = response.body()
                    val photoDetailEntityList =
                        searchPhotoList?.results?.map { photoDetailResponse ->
                            PhotoDetailEntity(
                                id = photoDetailResponse.id,
                                raw = photoDetailResponse.urls?.raw,
                                full = photoDetailResponse.urls?.full,
                                regular = photoDetailResponse.urls?.regular,
                                small = photoDetailResponse.urls?.small,
                                thumb = photoDetailResponse.urls?.thumb,
                            )
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


    private fun errorConverter(errorBody: ResponseBody?): ErrorResponse? {
        return errorBody?.let {
            retrofit.responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java,
                ErrorResponse::class.java.annotations
            ).convert(it)
        }
    }
}