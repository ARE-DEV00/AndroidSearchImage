package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import timber.log.Timber
import javax.inject.Inject

class GetPhotoInfoPagingListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository,
) {
    suspend operator fun invoke(perPage: Int = 20): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            dataRepository.getPhotoPagingList(perPage)
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