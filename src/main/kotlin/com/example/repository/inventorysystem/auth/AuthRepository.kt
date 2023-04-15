package com.example.repository.inventorysystem.auth

import com.example.base.BaseResponse
import com.example.models.inventorysystem.auth.CreateUserParams
import com.example.models.inventorysystem.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams) : BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams) : BaseResponse<Any>
    suspend fun createUser(role: String, params: CreateUserParams): BaseResponse<Any>
}