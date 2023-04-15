package com.example.models.inventorysystem.company

data class CompanyPayload(
    val companyId: Int,
    val userId: Int,
    val companyName: String,
    val companySubscription: Boolean,
    val companySubscriptionStatus: String
)
