package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import timber.log.Timber
import javax.inject.Inject

class GetSearchPhotoInfoPagingListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository,
) {
    suspend operator fun invoke(
        query: String,
        perPage: Int = 20,
    ): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            dataRepository.getSearchPhotoPagingList(query, perPage)
                .catch {
                    Timber.e(it)
                }
                .collectLatest {
                    Timber.d("#### GetSearchPhotoInfoPagingListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}