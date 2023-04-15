package com.example.repository.blogs.user

import com.example.base.BaseResponse
import com.example.models.blogs.users.LoginBlogUser
import com.example.models.blogs.users.RegisterBlogUser

interface BlogUserRepository {
    suspend fun registerUser(params: RegisterBlogUser) : BaseResponse<Any>
    suspend fun loginUser(params: LoginBlogUser) : BaseResponse<Any>
}