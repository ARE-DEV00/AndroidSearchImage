package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entity.SearchPhotoListEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import timber.log.Timber
import javax.inject.Inject

class GetSearchPhotoInfoListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<SearchPhotoListEntity> {
        return channelFlow {
            dataRepository.getSearchPhotoList(query, page, perPage)
                .catch {
                    Timber.e(it)
                }
                .collectLatest {
                    Timber.d("#### GetSearchPhotoInfoListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}