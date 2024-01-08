package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import timber.log.Timber
import javax.inject.Inject

class GetBookmarkInfoDbPagingListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(perPage: Int = 10): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            dataRepository.getBookmarkInfoPagingList(perPage)
                .catch {
                    it.printStackTrace()
                }
                .collectLatest {
                    Timber.d("#### GetBookmarkInfoDbPagingListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}