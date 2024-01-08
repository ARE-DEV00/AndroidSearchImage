package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
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
                    Logger.e("", it)
                }
                .collectLatest {
                    Logger.d("#### GetSearchPhotoInfoPagingListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}