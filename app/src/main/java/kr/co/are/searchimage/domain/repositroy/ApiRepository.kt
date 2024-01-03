package kr.co.are.searchimage.domain.repositroy


import kotlinx.coroutines.flow.Flow
import kr.co.are.searchimage.domain.entitiy.PhotoImageEntity

interface ApiRepository {
    suspend fun getPhotoList(
        page: Int,
        perPage: Int,
    ): Flow<List<PhotoImageEntity>>


}
