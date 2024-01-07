package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.SearchPhotoListEntity
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import javax.inject.Inject

class GetSearchPhotoInfoListUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<SearchPhotoListEntity> {
        return channelFlow {
            apiRepository.getSearchPhotoList(query, page, perPage)
                .catch {
                    Logger.e("", it)
                }
                .collectLatest {
                    Logger.d("#### GetSearchPhotoInfoListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}