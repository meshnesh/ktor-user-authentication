package com.example.repository.inventorysystem.sales

import com.example.base.BaseResponse
import com.example.models.inventorysystem.sales.SalesPayload
import com.example.service.inventorysystem.sales.SalesService
import com.example.utils.GENERIC_ERROR
import com.example.utils.SUCCESS

class SalesRepositoryImpl(private val salesService: SalesService) : SalesRepository {
    override suspend fun getAllSales(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = salesService.getAllSales(page, limit), message = SUCCESS)
    }

    override suspend fun createSale(salesPayload: SalesPayload): BaseResponse<Any> {
        val sale = salesService.createSale(salesPayload)
        return if (sale != null) BaseResponse.SuccessResponse(
            data = sale,
            message = SUCCESS
        ) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateSale(saleId: Int, salesPayload: SalesPayload): BaseResponse<Any> {
        return if (salesService.updateSale(saleId, salesPayload)) {
            BaseResponse.SuccessResponse(data = salesPayload, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteSale(saleId: Int): BaseResponse<Any> {
        return if (salesService.deleteSale(saleId)) {
            BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}