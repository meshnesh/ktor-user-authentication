package com.example.repository.company

import com.example.base.BaseResponse
import com.example.models.company.CompanyPayload

interface CompanyRepository {
    suspend fun getAllCompanies(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createCompany(userId: Int, companyPayload: CompanyPayload): BaseResponse<Any>
    suspend fun updateCompany(companyId: Int, userId: Int, companyPayload: CompanyPayload): BaseResponse<Any>
    suspend fun deleteCompany(companyId: Int): BaseResponse<Any>
}