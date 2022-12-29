package com.example.utils

import com.example.models.CreateUserParams
import com.example.models.UserLoginParams
import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse (
    var data: CreateUserParams,
    var message: String? = null
)
@Serializable
data class LoginSuccessResponse (
    var data: UserLoginParams,
    var message: String? = null
)
