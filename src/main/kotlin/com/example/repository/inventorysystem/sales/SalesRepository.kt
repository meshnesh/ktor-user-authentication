package com.example.repository.inventorysystem.sales

import com.example.base.BaseResponse
import com.example.models.inventorysystem.sales.SalesPayload

interface SalesRepository {
    suspend fun getAllSales(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createSale(salesPayload: SalesPayload): BaseResponse<Any>
    suspend fun updateSale(saleId: Int, salesPayload: SalesPayload): BaseResponse<Any>
    suspend fun deleteSale(saleId: Int): BaseResponse<Any>
}