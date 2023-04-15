package com.example.models.blogs.users

data class AddStoryPayload (
    val id: Int,
    val userId: Int,
    var title: String,
    var content: String,
    var isDraft: Boolean = true,
    var isUpdated: Boolean = false,
    val createdAt: String
)
