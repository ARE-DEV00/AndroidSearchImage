package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import timber.log.Timber
import javax.inject.Inject

class GetPhotoInfoListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(page: Int, perPage: Int): Flow<List<PhotoDetailEntity>> {
        return channelFlow {
            dataRepository.getPhotoList(page, perPage)
                .catch {
                    Timber.e(it)
                }
                .collectLatest {
                    Timber.d("#### GetPhotoInfoListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}