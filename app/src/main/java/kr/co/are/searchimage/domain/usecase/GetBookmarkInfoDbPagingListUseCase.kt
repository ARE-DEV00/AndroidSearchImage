package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class GetBookmarkInfoDbPagingListUseCase @Inject constructor(
    private val appDatabaseRepository: AppDatabaseRepository
) {
    suspend operator fun invoke(perPage: Int = 10): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            appDatabaseRepository.getBookmarkInfoPagingList(perPage)
                .catch {
                    it.printStackTrace()
                }
                .collectLatest {
                    Logger.d("#### GetBookmarkInfoDbPagingListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}