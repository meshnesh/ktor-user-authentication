package com.example.models.product

data class AvailableProductsPayload(
    val companyId: Int,
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val productPrice: Int,
    val productQuantity: Int,
    val productCode: String,
    val productImage: String
)
