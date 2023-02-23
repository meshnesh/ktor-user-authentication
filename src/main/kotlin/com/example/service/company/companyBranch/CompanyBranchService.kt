package com.example.service.company.companyBranch

import com.example.models.common.PaginatedResult
import com.example.models.company.companyBranch.CompanyBranchPayload

interface CompanyBranchService {

    suspend fun createCompanyBranch(params: CompanyBranchPayload): CompanyBranchPayload?
    suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranchPayload): Boolean
    suspend fun deleteCompanyBranch(id: Int): Boolean
    suspend fun fetchAllCompanyBranch(page: Int, limit: Int): PaginatedResult<CompanyBranchPayload>
}
