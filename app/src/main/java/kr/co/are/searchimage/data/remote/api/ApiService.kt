package kr.co.are.searchimage.data.remote.api


import kr.co.are.searchimage.data.model.response.GetPhotoListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    /* 앱 버전 업데이트 확인 */
    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<GetPhotoListResponse>>

}
