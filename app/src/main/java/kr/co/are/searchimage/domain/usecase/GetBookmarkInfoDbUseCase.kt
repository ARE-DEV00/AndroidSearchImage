package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class GetBookmarkInfoDbUseCase @Inject constructor(
    private val appDatabaseRepository: AppDatabaseRepository
) {
    suspend operator fun invoke(id: String): Flow<PhotoDetailEntity?> {
        return appDatabaseRepository.getBookmarkInfo(id = id)
    }
}