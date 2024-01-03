package kr.co.are.searchimage.data.remote.api.repository


import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.are.searchimage.data.model.exception.ErrorResponse
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.data.remote.utils.ApiExceptionUtil
import kr.co.are.searchimage.domain.entitiy.PhotoImageEntity
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
    ): Flow<List<PhotoImageEntity>> {

        return flow {
            try {
                val response = apiService.getPhotoList(page, perPage)


                if (response.isSuccessful) {
                    val getPhotoListResponse = response.body()

                    getPhotoListResponse?.get(0)?.let { Logger.d(it.id) }
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