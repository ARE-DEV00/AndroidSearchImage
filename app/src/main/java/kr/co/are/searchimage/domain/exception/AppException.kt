package kr.co.are.searchimage.domain.exception


class AppException(private val exceptionCode: Int, private val exceptionMessage: String?) :
    Exception() {

    override val message: String?
        get() = exceptionMessage

    val code: Int
        get() = exceptionCode

    override fun printStackTrace() {
        super.printStackTrace()
    }

}
