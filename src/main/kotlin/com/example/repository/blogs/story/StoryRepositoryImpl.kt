package com.example.repository.blogs.story

import com.example.base.BaseResponse
import com.example.models.blogs.story.Story
import com.example.routes.blogs.story.StoryParams
import com.example.service.blogs.story.StoryService
import com.example.utils.GENERIC_ERROR
import com.example.utils.SUCCESS

class StoryRepositoryImpl(
    private val storyService: StoryService
) : StoryRepository {
    override suspend fun getMyStories(userId: Int, page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = storyService.getMyStories(userId, page, limit), message = SUCCESS)
    }

    override suspend fun getAllStories(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = storyService.getAllStories(page, limit), message = SUCCESS)
    }

    override suspend fun getStory(storyId: Int): BaseResponse<Any> {
        val story = storyService.getStory(storyId)
        return BaseResponse.SuccessResponse(data = story, message = SUCCESS)
    }

    override suspend fun add(userId: Int, storyParams: StoryParams): BaseResponse<Any> {
        val story = storyService.add(userId, storyParams)
        return if (story != null) {
            BaseResponse.SuccessResponse(data = story, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        }
    }

    override suspend fun update(id: Int, storyParams: Story): BaseResponse<Any> {
        if (storyService.update(id, storyParams)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun delete(storyId: Int): BaseResponse<Any> {
        if (storyService.delete(storyId)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun like(userId: Int, storyId: Int): BaseResponse<Any> {
        if (storyService.like(userId, storyId)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun comment(userId: Int, storyId: Int, comment: String): BaseResponse<Any> {
        if (storyService.comment(userId, storyId, comment)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun getComments(storyId: Int): BaseResponse<Any> {
        val comments = storyService.getComments(storyId)
        return BaseResponse.SuccessResponse(data = storyService.getComments(storyId), message = SUCCESS)
    }
}