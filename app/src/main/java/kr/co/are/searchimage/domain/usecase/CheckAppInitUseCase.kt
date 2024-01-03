package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CheckAppInitUseCase @Inject constructor(
) {

    suspend operator fun invoke(): Flow<String> {
        return channelFlow {

            send("테스트입니다.")

            awaitClose()
        }
    }
}