package com.example.models.blogs.story

data class AddStory(
    val id: Int,
    val userId: Int,
    var title: String,
    var shortDescription: String,
    var content: String,
    var coverImgUrl: String,
    var category: String,
    var isDraft: Boolean = true,
    var isUpdated: Boolean = false,
    val createdAt: String
)