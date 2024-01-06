package kr.co.are.searchimage.domain.repositroy


import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.entitiy.SearchPhotoListEntity

interface ApiRepository {
    suspend fun getPhotoList(
        page: Int,
        perPage: Int,
    ): Flow<List<PhotoDetailEntity>>

    suspend fun getPagingPhotoList(
        perPage: Int,
    ): Flow<Pager<Int, PhotoDetailEntity>>

    suspend fun getPhotoDetail(
        id: String
    ): Flow<PhotoDetailEntity>

    suspend fun searchPhotoList(
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String = "latest"
    ): Flow<SearchPhotoListEntity>

}