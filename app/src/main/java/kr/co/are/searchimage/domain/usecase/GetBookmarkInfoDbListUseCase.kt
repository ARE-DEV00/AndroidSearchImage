package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class GetBookmarkInfoDbListUseCase @Inject constructor(
    private val appDatabaseRepository: AppDatabaseRepository
) {
    suspend operator fun invoke(): Flow<List<PhotoDetailEntity>> {
        return appDatabaseRepository.getBookmarkInfoList()
    }
}