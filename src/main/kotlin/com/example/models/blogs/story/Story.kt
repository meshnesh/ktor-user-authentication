package com.example.models.blogs.story

import com.example.models.blogs.users.BlogUser

data class Story(
    val id: Int,
    val blogUser: BlogUser? = null,
    var title: String,
    var content: String,
    var isDraft: Boolean = true,
    var isUpdated: Boolean = false,
    val createdAt: String
)