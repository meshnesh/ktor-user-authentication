package com.example.repository.inventorysystem.user

import com.example.base.BaseResponse
import com.example.service.inventorysystem.user.InventoryUserService

class InventoryUserRepositoryImpl(
    private val inventoryUserService: InventoryUserService
) : InventoryUserRepository {

    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = inventoryUserService.getUser(id))
    }
}
