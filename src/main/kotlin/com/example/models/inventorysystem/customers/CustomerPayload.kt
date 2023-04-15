package com.example.models.inventorysystem.customers

data class CustomerPayload(
    val companyId: Int,
    val customerId: Int,
    val customerName: String,
    val customerPhoneNumber: String,
    val customerAddress: String
)
