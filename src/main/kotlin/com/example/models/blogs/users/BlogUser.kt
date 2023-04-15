package com.example.models.blogs.users


data class BlogUser(
    val id: Int,
    val fullName: String,
    val avatar: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)