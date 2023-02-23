package com.example.models.orders

data class OrdersPayload(
    val orderId: Int,
    val orderName: String,
    val orderDateTime: String,
    val orderTotalPrice: Int,
    val orderStatus: String
)
