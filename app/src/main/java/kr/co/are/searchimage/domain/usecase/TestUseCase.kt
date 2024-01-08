package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TestUseCase @Inject constructor(
) {
    suspend operator fun invoke(title: String): Flow<String> {
        return flow {
            emit("$title in usecase")
        }
    }
}