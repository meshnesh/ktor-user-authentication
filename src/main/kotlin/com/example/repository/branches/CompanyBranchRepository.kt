package com.example.repository.branches

import com.example.base.BaseResponse
import com.example.models.common.PaginatedResult
import com.example.models.companyBranch.CompanyBranch

interface CompanyBranchRepository {
    suspend fun createCompanyBranch(params: CompanyBranch): BaseResponse<Any>
    suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranch): BaseResponse<Any>
    suspend fun deleteCompanyBranch(id: Int): BaseResponse<Any>
    suspend fun fetchAllCompanyBranch(page: Int, limit: Int): BaseResponse<Any>
}