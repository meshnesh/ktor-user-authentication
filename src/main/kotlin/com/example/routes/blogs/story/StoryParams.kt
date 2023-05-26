package com.example.routes.blogs.story

import kotlinx.serialization.Serializable

@Serializable
data class StoryParams(
    val userId: Int,
    val title: String,
    var shortDescription: String,
    var content: String,
    var coverImgUrl: String,
    var category: String,
    val isDraft: Boolean,
    val isUpdated:Boolean,
)