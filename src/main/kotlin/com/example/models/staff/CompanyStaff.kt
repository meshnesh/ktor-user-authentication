package com.example.models.staff

data class CompanyStaff(
    val companyId: Int,
    val userId: Int,
    val id: Int,
    val idNo: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val email: String,
    val password: String,
    var authToken: String? = null,
    val createdAt: String,
    val joinDate: String,
    val isAdmin: Boolean,
    val role: String
)
