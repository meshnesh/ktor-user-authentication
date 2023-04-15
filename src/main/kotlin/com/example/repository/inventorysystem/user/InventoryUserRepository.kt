package com.example.repository.inventorysystem.user

import com.example.base.BaseResponse

interface InventoryUserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
}