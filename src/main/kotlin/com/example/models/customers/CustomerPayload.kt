package com.example.models.customers

data class CustomerPayload(
    val customerId: Int,
    val customerName: String,
    val customerPhoneNumber: String,
    val customerAddress: String
)
