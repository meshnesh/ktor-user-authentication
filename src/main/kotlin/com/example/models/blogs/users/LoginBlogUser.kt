package com.example.models.blogs.users

import kotlinx.serialization.Serializable

@Serializable
data class LoginBlogUser(
    val email: String,
    val password: String
)
