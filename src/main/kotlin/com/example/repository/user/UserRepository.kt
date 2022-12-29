package com.example.repository.user

import com.example.models.CreateUserParams
import com.example.models.UserLoginParams

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams) : String
    suspend fun loginUser(params: UserLoginParams) : String
}