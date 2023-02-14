package com.example.models.product

data class PurchasedProducts(
    val productId: String,
    val productName: String,
    val productDescription: String,
    val productPrice: String,
    val userId: String
)
