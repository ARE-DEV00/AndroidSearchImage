package kr.co.are.searchimage.data.remote.api


import kr.co.are.searchimage.data.remote.api.model.response.PhotoDetailResponse
import kr.co.are.searchimage.data.remote.api.model.response.SearchPhotoListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<PhotoDetailResponse>>

    @GET("photos/{id}")
    suspend fun getPhotoDetail(
        @Path("id") id: String,
    ): Response<PhotoDetailResponse>

    @GET("search/photos")
    suspend fun searchPhotoList(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String,
    ): Response<SearchPhotoListResponse>

}
