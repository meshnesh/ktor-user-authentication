package com.example.service

import com.example.models.CreateUserParams
import com.example.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun loginUser(email: String, password: String): User?
    suspend fun findUserByEmail(email: String): User?
}