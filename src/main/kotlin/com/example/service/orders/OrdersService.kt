package com.example.service.orders

import com.example.models.common.PaginatedResult
import com.example.models.orders.OrdersPayload

interface OrdersService {
    suspend fun getAllOrders(page: Int, limit: Int): PaginatedResult<OrdersPayload>
    suspend fun createOrder(ordersPayload: OrdersPayload): OrdersPayload?
    suspend fun updateOrder(orderId: Int, ordersPayload: OrdersPayload): Boolean
    suspend fun deleteOrder(orderId: Int): Boolean
}