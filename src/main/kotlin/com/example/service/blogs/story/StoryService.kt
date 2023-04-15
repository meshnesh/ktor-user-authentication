package com.example.service.blogs.story

import com.example.models.blogs.comment.Comment
import com.example.models.blogs.story.Story
import com.example.models.blogs.users.AddStoryPayload
import com.example.models.common.PaginatedResult
import com.example.routes.blogs.story.StoryParams

interface StoryService {
    suspend fun get(id: Int): Story?
    suspend fun getMyStories(userId: Int, page: Int, limit: Int): PaginatedResult<Story>
    suspend fun getAllStories(page: Int, limit: Int): PaginatedResult<Story>
    suspend fun getLikedStories(userId: Int): List<Story>
    suspend fun add(userId: Int, storyParams: StoryParams): AddStoryPayload?
    suspend fun update(id: Int, storyParams: Story): Boolean
    suspend fun delete(storyId: Int): Boolean
    suspend fun like(userId: Int, storyId: Int): Boolean
    suspend fun comment(userId: Int, storyId: Int, comment: String): Boolean
    suspend fun getComments(storyId: Int): List<Comment>
}