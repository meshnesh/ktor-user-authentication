package com.example.service.inventorysystem.sales

import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.sales.SalesPayload

interface SalesService {
    suspend fun getAllSales(page: Int, limit: Int): PaginatedResult<SalesPayload>
    suspend fun createSale(salesPayload: SalesPayload): SalesPayload?
    suspend fun updateSale(saleId: Int, salesPayload: SalesPayload): Boolean
    suspend fun deleteSale(saleId: Int): Boolean
}