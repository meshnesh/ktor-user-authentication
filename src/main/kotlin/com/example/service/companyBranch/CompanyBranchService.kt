package com.example.service.companyBranch

import com.example.models.common.PaginatedResult
import com.example.models.companyBranch.CompanyBranch
import com.example.models.product.AvailableProducts

interface CompanyBranchService {

    suspend fun createCompanyBranch(params: CompanyBranch): CompanyBranch?
    suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranch): Boolean
    suspend fun deleteCompanyBranch(id: Int): Boolean
    suspend fun fetchAllCompanyBranch(page: Int, limit: Int): PaginatedResult<CompanyBranch>
}
