//package kr.co.are.searchimage.domain.repositroy
//
//import androidx.paging.Pager
//import androidx.paging.PagingData
//import kotlinx.coroutines.flow.Flow
//import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity
//import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
//
//interface AppDatabaseRepository {
//
//    suspend fun addBookmarkInfo(
//        id: String,
//        author: String,
//        width: Int,
//        height: Int,
//        createdAt: String,
//        description: String,
//
//        imageUrlRaw: String,
//        imageUrlFull: String,
//        imageUrlRegular: String,
//        imageUrlSmall: String,
//        imageUrlThumb: String,
//    ): Flow<Boolean>
//
//    suspend fun deleteBookmarkInfo(
//        id: String,
//    ): Flow<Boolean>
//
//    suspend fun getBookmarkInfo(
//        id: String,
//    ): Flow<PhotoDetailEntity?>
//
//    suspend fun getBookmarkInfoList(
//    ): Flow<List<PhotoDetailEntity>>
//
//    suspend fun getBookmarkInfoPagingList(
//        perPage: Int
//    ): Flow<Pager<Int, PhotoDetailEntity>>
//}