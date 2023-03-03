package com.example.models.staff

data class CompanyStaff(
    val companyId: Int,
    val userId: Int,
    val staffId: Int,
    val staffIdNo: String,
    val staffFirstName: String,
    val staffLastName: String,
    val staffAvatar: String,
    val staffEmail: String,
    val staffPassword: String,
    val staffCreatedAt: String,
    val staffJoinDate: String,
    val staffIsAdmin: Boolean,
    val staffRole: String
)
