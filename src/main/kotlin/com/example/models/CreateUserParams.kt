package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserParams(
    val fullName: String,
    val idNo: String,
    val email: String,
    val password: String,
    val joinDate: String,
    val isAdmin: Boolean,
    val role: String,
    val avatar: String
)
