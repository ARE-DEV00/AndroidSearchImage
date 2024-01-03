package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(
    private val apiRepository:ApiRepository
) {

    suspend operator fun invoke(title:String): Flow<String> {
        return channelFlow {

            apiRepository.getPhotoList(1,2)
                .catch {  }
                .collectLatest {
                    Logger.d(it.size)
                }

            send("$title in usecase")


            awaitClose()
        }
    }
}