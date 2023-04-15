package com.example.service.blogs.user

import com.example.models.blogs.users.BlogUser
import com.example.models.blogs.users.RegisterBlogUser

interface BlogUserService {
    suspend fun registerUser(params: RegisterBlogUser): BlogUser?
    suspend fun loginUser(email: String, password: String): BlogUser?
    suspend fun findUserByEmail(email: String): BlogUser?
//    suspend fun findUserByRole(role: String): User?
}