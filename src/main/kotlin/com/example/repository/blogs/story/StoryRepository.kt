package com.example.repository.blogs.story

import com.example.base.BaseResponse
import com.example.models.blogs.story.Story
import com.example.routes.blogs.story.StoryParams

interface StoryRepository {

    suspend fun getMyStories(userId: Int, page: Int, limit: Int): BaseResponse<Any>
    suspend fun getAllStories(page: Int, limit: Int): BaseResponse<Any>
    suspend fun getStory(storyId: Int): BaseResponse<Any>
    suspend fun add(userId: Int, storyParams: StoryParams): BaseResponse<Any>
    suspend fun update(id: Int, storyParams: Story): BaseResponse<Any>
    suspend fun delete(storyId: Int): BaseResponse<Any>
    suspend fun like(userId: Int, storyId: Int): BaseResponse<Any>
    suspend fun comment(userId: Int, storyId: Int, comment: String): BaseResponse<Any>
    suspend fun getComments(storyId: Int): BaseResponse<Any>
}