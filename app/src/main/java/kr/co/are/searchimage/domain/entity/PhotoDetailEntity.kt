package kr.co.are.searchimage.domain.entity

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
        val id: String = "",
        val author: String = "",
        val width: Int = 0,
        val height: Int = 0,
        val createdAt: String = "",
        val description: String = "",

        var isBookmark:Boolean = false
    )

}