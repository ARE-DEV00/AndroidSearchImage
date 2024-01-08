package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import javax.inject.Inject

class GetBookmarkInfoDbListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(): Flow<List<PhotoDetailEntity>> {
        return dataRepository.getBookmarkInfoList()
    }
}