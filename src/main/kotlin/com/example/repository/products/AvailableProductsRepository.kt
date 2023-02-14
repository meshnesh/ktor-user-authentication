package com.example.repository.products

import com.example.base.BaseResponse
import com.example.models.product.AvailableProducts

interface AvailableProductsRepository {

    suspend fun getAllActiveProducts(page: Int, limit: Int): BaseResponse<Any>
    suspend fun addProducts(availableProducts: AvailableProducts): BaseResponse<Any>
    suspend fun update(productId: Int, availableProducts: AvailableProducts): BaseResponse<Any>
    suspend fun delete(productId: Int): BaseResponse<Any>
}
