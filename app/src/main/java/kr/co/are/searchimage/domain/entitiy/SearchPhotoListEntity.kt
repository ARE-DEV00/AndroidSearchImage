package kr.co.are.searchimage.domain.entitiy

data class SearchPhotoListEntity(
    val total: Int,
    val totalPages: Int,
    val list: List<PhotoDetailEntity>
) {

}