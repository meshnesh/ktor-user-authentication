package com.example.service.auth

import com.example.models.auth.CreateUserParams
import com.example.models.user.User

interface AuthService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun loginUser(email: String, password: String): User?
    suspend fun findUserByEmail(email: String): User?
//    suspend fun findUserByRole(role: String): User?
}