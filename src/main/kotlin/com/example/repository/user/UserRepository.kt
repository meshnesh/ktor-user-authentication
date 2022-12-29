package com.example.repository.user

import com.example.models.CreateUserParams
import com.example.models.UserLoginParams
import com.example.utils.LoginSuccessResponse
import com.example.utils.SuccessResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams) : SuccessResponse
    suspend fun loginUser(params: UserLoginParams) : LoginSuccessResponse
}