package com.example.repository.products

import com.example.base.BaseResponse
import com.example.models.product.AvailableProductsPayload

interface ProductsRepository {

    suspend fun getAllActiveProducts(page: Int, limit: Int): BaseResponse<Any>
    suspend fun getCompanyProducts(companyId: Int, page: Int, limit: Int): BaseResponse<Any>
    suspend fun addProducts(availableProductsPayload: AvailableProductsPayload): BaseResponse<Any>
    suspend fun update(productId: Int, availableProductsPayload: AvailableProductsPayload): BaseResponse<Any>
    suspend fun delete(productId: Int): BaseResponse<Any>
}
