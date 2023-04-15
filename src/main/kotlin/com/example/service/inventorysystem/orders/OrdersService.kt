package com.example.service.inventorysystem.orders

import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.orders.OrdersPayload

interface OrdersService {
    suspend fun getAllOrders(page: Int, limit: Int): PaginatedResult<OrdersPayload>
    suspend fun createOrder(ordersPayload: OrdersPayload): OrdersPayload?
    suspend fun updateOrder(orderId: Int, ordersPayload: OrdersPayload): Boolean
    suspend fun deleteOrder(orderId: Int): Boolean
}