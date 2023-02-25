package com.example.repository.company

import com.example.base.BaseResponse
import com.example.models.company.CompanyPayload
import com.example.service.company.CompanyService
import com.example.utils.*

class CompanyRepositoryImpl( private val companyService: CompanyService) : CompanyRepository {
    override suspend fun getAllCompanies(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = companyService.getAllCompanies(page, limit), message = SUCCESS)
    }

    override suspend fun createCompany(userId: Int, companyPayload: CompanyPayload): BaseResponse<Any> {
        val company = companyService.createCompany(userId, companyPayload)
        return if (company != null) BaseResponse.SuccessResponse(data = company, message = COMPANY_CREATION_SUCCESS) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateCompany(companyId: Int, userId: Int, companyPayload: CompanyPayload): BaseResponse<Any> {
        return if (companyService.updateCompany(companyId, userId, companyPayload)) {
            BaseResponse.SuccessResponse(data = companyPayload, message = COMPANY_UPDATE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)

    }

    override suspend fun deleteCompany(companyId: Int): BaseResponse<Any> {
        return if (companyService.deleteCompany(companyId)) {
            BaseResponse.SuccessResponse(data = null, message = COMPANY_DELETE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}
