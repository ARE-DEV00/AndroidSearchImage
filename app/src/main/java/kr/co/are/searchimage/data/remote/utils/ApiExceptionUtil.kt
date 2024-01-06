package kr.co.are.searchimage.data.remote.utils

import kr.co.are.searchimage.data.remote.api.model.exception.ErrorResponse
import kr.co.are.searchimage.domain.enums.ExceptionCodeStatus
import kr.co.are.searchimage.domain.exception.AppException

object ApiExceptionUtil {
    fun apiException(errorResponse: ErrorResponse?): AppException {
        when (errorResponse?.code) {
            ExceptionCodeStatus.UnknownApiException.code -> {
                return AppException(
                    ExceptionCodeStatus.UnknownApiException.code,
                    ExceptionCodeStatus.UnknownApiException.name
                )
            }

            ExceptionCodeStatus.RetryNoConnectivityException.code -> {
                return AppException(
                    ExceptionCodeStatus.RetryNoConnectivityException.code,
                    ExceptionCodeStatus.RetryNoConnectivityException.name
                )
            }

            ExceptionCodeStatus.InnerNoConnectivityException.code -> {
                return AppException(
                    ExceptionCodeStatus.InnerNoConnectivityException.code,
                    ExceptionCodeStatus.InnerNoConnectivityException.name
                )
            }

            ExceptionCodeStatus.UnknownAppException.code -> {
                return AppException(
                    ExceptionCodeStatus.UnknownAppException.code,
                    ExceptionCodeStatus.UnknownAppException.name
                )
            }
            else -> {
                return AppException(
                    ExceptionCodeStatus.UnknownAppException.code,
                    ExceptionCodeStatus.UnknownAppException.name
                )
            }
        }
    }
}