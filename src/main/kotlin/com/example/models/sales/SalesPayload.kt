package com.example.models.sales

data class SalesPayload(
    val saleId: Int,
    val saleName: String,
    val saleDateTime: String,
    val saleTotalPrice: Int,
    val saleStatus: String
)
