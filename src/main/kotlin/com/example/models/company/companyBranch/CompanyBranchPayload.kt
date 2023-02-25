package com.example.models.company.companyBranch

data class CompanyBranchPayload(
    val companyId: Int,
    val branchId: Int,
    val branchName: String,
    val branchLocation: String
)
