package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int,
    val idNo: String,
    val fullName: String,
    val avatar: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String,
    val joinDate: String,
    val isAdmin: Boolean,
    val role: String
)
