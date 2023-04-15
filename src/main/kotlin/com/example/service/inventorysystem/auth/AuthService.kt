package com.example.service.inventorysystem.auth

import com.example.models.inventorysystem.auth.CreateUserParams
import com.example.models.inventorysystem.user.InventoryUser

interface AuthService {
    suspend fun registerUser(params: CreateUserParams): InventoryUser?
    suspend fun loginUser(email: String, password: String): InventoryUser?
    suspend fun findUserByEmail(email: String): InventoryUser?
}