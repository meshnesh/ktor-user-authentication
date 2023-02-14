package com.example.models.product

data class AvailableProducts(
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val productPrice: Int,
    val productQuantity: Int
)
