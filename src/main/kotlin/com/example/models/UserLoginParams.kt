package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginParams(
    val email: String,
    val password: String
)
