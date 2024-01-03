package kr.co.are.searchimage.domain.enums

enum class ExceptionCodeStatus(val code: Int) {

    //API Exception Code
    UnknownApiException(40100),

    //APP Exception Code
    UnknownAppException(99999),
    InnerNoConnectivityException(99998),
    RetryNoConnectivityException(99997),



}