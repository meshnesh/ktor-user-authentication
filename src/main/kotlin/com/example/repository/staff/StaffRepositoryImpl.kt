package com.example.repository.staff

import com.example.base.BaseResponse
import com.example.models.staff.CompanyStaff
import com.example.service.staff.StaffService
import com.example.utils.*

class StaffRepositoryImpl(private val staffService: StaffService) : StaffRepository {
    override suspend fun getAllStaff(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = staffService.getAllStaff(page, limit), message = SUCCESS)
    }

    override suspend fun createCompanyStaff(userId: Int, staff: CompanyStaff): BaseResponse<Any> {
        val company = staffService.createCompanyStaff(userId, staff)
        return if (company != null) BaseResponse.SuccessResponse(data = company, message = STAFF_CREATION_SUCCESS) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateCompanyStaff(staffId: Int, userId: Int, staff: CompanyStaff): BaseResponse<Any> {
        return if (staffService.updateCompanyStaff(staffId, userId, staff)) {
            BaseResponse.SuccessResponse(data = staff, message = STAFF_UPDATE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteCompanyStaff(staffId: Int): BaseResponse<Any> {
        return if (staffService.deleteCompanyStaff(staffId)) {
            BaseResponse.SuccessResponse(data = null, message = STAFF_DELETE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}
