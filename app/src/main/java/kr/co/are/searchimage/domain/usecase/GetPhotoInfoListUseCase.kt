package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import javax.inject.Inject

class GetPhotoInfoListUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(page: Int, perPage: Int): Flow<List<PhotoDetailEntity>> {
        return channelFlow {
            dataRepository.getPhotoList(page, perPage)
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