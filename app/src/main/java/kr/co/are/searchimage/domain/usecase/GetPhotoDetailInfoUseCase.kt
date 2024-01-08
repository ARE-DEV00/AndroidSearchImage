package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import javax.inject.Inject

class GetPhotoDetailInfoUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(id: String): Flow<PhotoDetailEntity> {
        return channelFlow {
            dataRepository.getPhotoDetail(id)
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