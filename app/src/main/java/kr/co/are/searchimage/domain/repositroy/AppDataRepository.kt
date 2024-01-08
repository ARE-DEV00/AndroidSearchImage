package kr.co.are.searchimage.domain.repositroy


import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.entity.SearchPhotoListEntity

interface AppDataRepository {
    //API
    suspend fun getPhotoList(
        page: Int,
        perPage: Int,
    ): Flow<List<PhotoDetailEntity>>

    suspend fun getPhotoPagingList(
        perPage: Int,
    ): Flow<Pager<Int, PhotoDetailEntity>>

    suspend fun getPhotoDetail(
        id: String
    ): Flow<PhotoDetailEntity>

    suspend fun getSearchPhotoList(
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String = "latest"
    ): Flow<SearchPhotoListEntity>

    suspend fun getSearchPhotoPagingList(
        query: String,
        perPage: Int,
        orderBy: String = "latest"
    ): Flow<Pager<Int, PhotoDetailEntity>>

    //ROOM
    suspend fun addBookmarkInfo(
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
        imageUrlThumb: String,
    ): Flow<Boolean>

    suspend fun deleteBookmarkInfo(
        id: String,
    ): Flow<Boolean>

    suspend fun getBookmarkInfo(
        id: String,
    ): Flow<PhotoDetailEntity?>

    suspend fun getBookmarkInfoList(
    ): Flow<List<PhotoDetailEntity>>

    suspend fun getBookmarkInfoPagingList(
        perPage: Int
    ): Flow<Pager<Int, PhotoDetailEntity>>

}
