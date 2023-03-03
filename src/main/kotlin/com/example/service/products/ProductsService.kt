package com.example.service.products

import com.example.models.common.PaginatedResult
import com.example.models.product.AvailableProductsPayload

interface ProductsService {
    suspend fun getAvailableProducts(page: Int, limit: Int): PaginatedResult<AvailableProductsPayload>
    suspend fun getCompanyProducts(companyId: Int, page: Int, limit: Int): PaginatedResult<AvailableProductsPayload>
    suspend fun add(availableProductsPayload: AvailableProductsPayload): AvailableProductsPayload?
    suspend fun update(productId: Int, availableProductsPayload: AvailableProductsPayload): Boolean
    suspend fun delete(productId: Int): Boolean
}
