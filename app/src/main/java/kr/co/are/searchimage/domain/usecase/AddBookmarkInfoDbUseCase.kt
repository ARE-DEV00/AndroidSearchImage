package kr.co.are.searchimage.domain.usecase

import kotlinx.coroutines.flow.*
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import javax.inject.Inject

class AddBookmarkInfoDbUseCase @Inject constructor(
    private val dataRepository: AppDataRepository
) {
    suspend operator fun invoke(photoDetailEntity: PhotoDetailEntity): Flow<Boolean> {
        return dataRepository.addBookmarkInfo(
            id = photoDetailEntity.imageInfo.id,
            author = photoDetailEntity.imageInfo.author,
            width = photoDetailEntity.imageInfo.width,
            height = photoDetailEntity.imageInfo.height,
            createdAt = photoDetailEntity.imageInfo.createdAt,
            description = photoDetailEntity.imageInfo.description,

            imageUrlRaw = photoDetailEntity.imageUrl.raw,
            imageUrlFull = photoDetailEntity.imageUrl.full,
            imageUrlRegular = photoDetailEntity.imageUrl.regular,
            imageUrlSmall = photoDetailEntity.imageUrl.small,
            imageUrlThumb = photoDetailEntity.imageUrl.thumb,
        )
    }
}