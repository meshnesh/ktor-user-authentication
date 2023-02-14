package com.example.service.products

import com.example.models.common.PaginatedResult
import com.example.models.product.AvailableProducts

interface AvailableProductsService {
    suspend fun getAvailableProducts(page: Int, limit: Int): PaginatedResult<AvailableProducts>
    suspend fun add(availableProducts: AvailableProducts): AvailableProducts?
    suspend fun update(id: Int, availableProducts: AvailableProducts): Boolean
    suspend fun delete(storyId: Int): Boolean
}
