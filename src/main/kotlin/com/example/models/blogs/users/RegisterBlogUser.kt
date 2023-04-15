package com.example.models.blogs.users

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBlogUser(
    val fullName: String,
    val email: String,
    val password: String,
    val avatar: String
)
