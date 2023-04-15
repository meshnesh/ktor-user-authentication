package com.example.service.inventorysystem.user

import com.example.models.inventorysystem.user.InventoryUser

interface InventoryUserService {
    suspend fun getUser(id: Int): InventoryUser
}