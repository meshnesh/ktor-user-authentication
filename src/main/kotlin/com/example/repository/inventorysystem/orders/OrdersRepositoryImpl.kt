package com.example.repository.inventorysystem.orders

import com.example.base.BaseResponse
import com.example.models.inventorysystem.orders.OrdersPayload
import com.example.service.inventorysystem.orders.OrdersService
import com.example.utils.GENERIC_ERROR
import com.example.utils.SUCCESS

class OrdersRepositoryImpl(private val ordersService: OrdersService) : OrdersRepository {
    override suspend fun getAllOrders(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = ordersService.getAllOrders(page, limit), message = SUCCESS)
    }

    override suspend fun createOrder(ordersPayload: OrdersPayload): BaseResponse<Any> {
        val order = ordersService.createOrder(ordersPayload)
        return if (order != null) BaseResponse.SuccessResponse(
            data = order,
            message = SUCCESS
        ) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateOrder(orderId: Int, ordersPayload: OrdersPayload): BaseResponse<Any> {
        return if (ordersService.updateOrder(orderId, ordersPayload)) {
            BaseResponse.SuccessResponse(data = ordersPayload, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteOrder(orderId: Int): BaseResponse<Any> {
        return if (ordersService.deleteOrder(orderId)) {
            BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}