package com.example.service.inventorysystem.company.companyBranch

import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.company.companyBranch.CompanyBranchPayload

interface CompanyBranchService {

    suspend fun createCompanyBranch(params: CompanyBranchPayload): CompanyBranchPayload?
    suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranchPayload): Boolean
    suspend fun deleteCompanyBranch(id: Int): Boolean
    suspend fun fetchAllCompanyBranch(page: Int, limit: Int): PaginatedResult<CompanyBranchPayload>
}
