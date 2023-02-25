package com.example.models.company

data class CompanyPayload(
    val companyId: Int,
    val userId: Int,
    val companyName: String,
    val companySubscription: Boolean,
    val companySubscriptionStatus: String
)
