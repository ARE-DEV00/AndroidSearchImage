package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import androidx.paging.map
import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.entitiy.SearchPhotoListEntity
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class GetSearchPhotoInfoPagingListUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
) {
    suspend operator fun invoke(
        query: String,
        perPage: Int = 20,
    ): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            apiRepository.getSearchPhotoPagingList(query, perPage)
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