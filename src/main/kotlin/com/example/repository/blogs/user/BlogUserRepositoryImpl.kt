package com.example.repository.blogs.user

import com.example.base.BaseResponse
import com.example.models.blogs.users.LoginBlogUser
import com.example.models.blogs.users.RegisterBlogUser
import com.example.security.JwtConfig
import com.example.service.blogs.user.BlogUserService
import com.example.utils.*

class BlogUserRepositoryImpl(
    private val blogUserService: BlogUserService
) : BlogUserRepository {
    override suspend fun registerUser(params: RegisterBlogUser): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = blogUserService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = USER_REGISTRATION_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: LoginBlogUser): BaseResponse<Any> {
        val user = blogUserService.loginUser(params.email, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return blogUserService.findUserByEmail(email) != null
    }
}
