package com.example.utils

import io.ktor.http.*
import kotlinx.serialization.SerialName

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class SuccessResponse<T>(
        @SerialName("data") var data: T? = null,
        @SerialName("message") var message: String? = null
    ) : BaseResponse<T>()

    data class ErrorResponse<T>(
        val exception: T? = null,
        val message: String
    ) : BaseResponse<T>()
}
