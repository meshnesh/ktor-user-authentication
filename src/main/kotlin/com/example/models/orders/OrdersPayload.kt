package com.example.models.orders

data class OrdersPayload(
    val companyId: Int,
    val staffId: Int,
    val orderId: Int,
    val orderName: String,
    val orderDateTime: String,
    val orderTotalPrice: Int,
    val orderStatus: String
)
