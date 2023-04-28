package com.example.repository.inventorysystem.branches

import com.example.base.BaseResponse
import com.example.models.inventorysystem.company.companyBranch.CompanyBranchPayload

interface CompanyBranchRepository {
    suspend fun createCompanyBranch(params: CompanyBranchPayload): BaseResponse<Any>
    suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranchPayload): BaseResponse<Any>
    suspend fun deleteCompanyBranch(id: Int): BaseResponse<Any>
    suspend fun fetchAllCompanyBranch(page: Int, limit: Int): BaseResponse<Any>
}