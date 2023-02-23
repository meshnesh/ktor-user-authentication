package com.example.repository.branches

import com.example.base.BaseResponse
import com.example.models.company.companyBranch.CompanyBranchPayload
import com.example.service.company.companyBranch.CompanyBranchService
import com.example.utils.GENERIC_ERROR
import com.example.utils.PRODUCT_DELETE_SUCCESS
import com.example.utils.PRODUCT_UPDATE_SUCCESS
import com.example.utils.SUCCESS

class CompanyBranchRepositoryImpl(
    private val companyBranchService: CompanyBranchService
) : CompanyBranchRepository {
    override suspend fun createCompanyBranch(params: CompanyBranchPayload): BaseResponse<Any> {
        val companyBranch = companyBranchService.createCompanyBranch(params)
        return if (companyBranch != null) {
            BaseResponse.SuccessResponse(data = companyBranch, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        }
    }

    override suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranchPayload): BaseResponse<Any> {
        if (companyBranchService.editCompanyBranch(id, companyBranch)) {
            return BaseResponse.SuccessResponse(data = companyBranch, message = PRODUCT_UPDATE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteCompanyBranch(id: Int): BaseResponse<Any> {
        if (companyBranchService.deleteCompanyBranch(id)) {
            return BaseResponse.SuccessResponse(data = null, message = PRODUCT_DELETE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun fetchAllCompanyBranch(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = companyBranchService.fetchAllCompanyBranch(page, limit), message = SUCCESS)
    }
}
