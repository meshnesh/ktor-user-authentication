package com.example.repository.orders

import com.example.base.BaseResponse
import com.example.models.orders.OrdersPayload

interface OrdersRepository {
    suspend fun getAllOrders(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createOrder(ordersPayload: OrdersPayload): BaseResponse<Any>
    suspend fun updateOrder(orderId: Int, ordersPayload: OrdersPayload): BaseResponse<Any>
    suspend fun deleteOrder(orderId: Int): BaseResponse<Any>
}