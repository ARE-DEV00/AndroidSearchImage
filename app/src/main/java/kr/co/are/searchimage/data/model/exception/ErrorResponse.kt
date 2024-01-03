package kr.co.are.searchimage.data.model.exception

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: List<String>? = null,
    val code: Int?,
    val message: String?
)