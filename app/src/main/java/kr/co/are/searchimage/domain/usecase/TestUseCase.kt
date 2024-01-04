package kr.co.are.searchimage.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.repositroy.ApiRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(
) {
    suspend operator fun invoke(title: String): Flow<String> {
        return flow {
            emit("$title in usecase")
        }
    }
}