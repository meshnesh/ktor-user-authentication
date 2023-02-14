package com.example.service.user

import com.example.models.user.User

interface UserService {
    suspend fun getUser(id: Int): User
}