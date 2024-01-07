package kr.co.are.searchimage.domain.usecase

import androidx.paging.Pager
import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class GetPhotoInfoPagingListUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val appDatabaseRepository: AppDatabaseRepository
) {
    suspend operator fun invoke(perPage: Int = 20): Flow<Pager<Int, PhotoDetailEntity>> {
        return channelFlow {
            apiRepository.getPhotoPagingList(perPage)
                .catch {
                    Logger.e("", it)
                }
                .collectLatest {
                    Logger.d("#### GetPhotoInfoListUseCase")
                    send(it)
                }
            awaitClose()
        }
    }
}