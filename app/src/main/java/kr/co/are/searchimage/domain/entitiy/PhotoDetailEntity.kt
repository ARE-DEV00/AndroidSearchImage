package kr.co.are.searchimage.domain.entitiy

data class PhotoDetailEntity(
    val imageInfo: ImageInfo,
    val imageUrl: ImageUrl,
) {
    data class ImageUrl(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    )

    data class ImageInfo(
        val id: String,
        val author: String,
        val width: Int,
        val height: Int,
        val createdAt: String,
        val description: String,
    )

}