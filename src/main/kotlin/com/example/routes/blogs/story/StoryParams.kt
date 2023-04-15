package com.example.routes.blogs.story

data class StoryParams(
    val userId: Int,
    val title: String,
    val content: String,
    val isDraft: Boolean,
    val isUpdated:Boolean
)