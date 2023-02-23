package com.example.models.company

data class CompanyPayload(
    val companyId: Int,
    val companyName: String,
    val companySubscription: Boolean,
    val companySubscriptionStatus: String
)
