package com.example.repository.auth

import com.example.base.BaseResponse
import com.example.models.auth.CreateUserParams
import com.example.models.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams) : BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams) : BaseResponse<Any>
    suspend fun createUser(role: String, params: CreateUserParams): BaseResponse<Any>
}