package kr.co.are.searchimage.domain.entitiy

data class PhotoDetailEntity(
    val id: String,

    val raw:String?,
    val full:String?,
    val regular:String?,
    val small:String?,
    val thumb:String?,
) {

}