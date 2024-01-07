package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class DeleteBookmarkInfoDbUseCase @Inject constructor(
    private val appDatabaseRepository: AppDatabaseRepository
) {
    suspend operator fun invoke(id: String): Flow<Boolean> {
        return appDatabaseRepository.deleteBookmarkInfo(
            id = id
        )
    }
}