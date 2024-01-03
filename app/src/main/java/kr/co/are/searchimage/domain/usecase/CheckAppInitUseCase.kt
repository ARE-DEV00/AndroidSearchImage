package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TestUseCase @Inject constructor(
) {

    suspend operator fun invoke(title:String): Flow<String> {
        return channelFlow {

            send("$title in usecase")

            awaitClose()
        }
    }
}