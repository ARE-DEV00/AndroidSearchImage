package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import javax.inject.Inject

class DeleteBookmarkInfoDbUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(id: String): Flow<Boolean> {
        return dataRepository.deleteBookmarkInfo(
            id = id
        )
    }
}