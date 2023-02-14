package com.example.models.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginParams(
    val email: String,
    val password: String
)
