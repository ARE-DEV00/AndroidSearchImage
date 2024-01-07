package kr.co.are.searchimage.domain.repositroy

import kotlinx.coroutines.flow.Flow

interface AppDatabaseRepository {

    suspend fun addBookmarkInfo(

    ): Flow<Boolean>

    suspend fun removeBookmarkInfo(

    ): Flow<Boolean>


}