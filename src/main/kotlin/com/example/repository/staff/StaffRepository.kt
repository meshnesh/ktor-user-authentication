package com.example.repository.staff

import com.example.base.BaseResponse
import com.example.models.staff.CompanyStaff

interface StaffRepository {
    suspend fun getAllStaff(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createCompanyStaff(userId: Int, staff: CompanyStaff): BaseResponse<Any>
    suspend fun updateCompanyStaff(staffId: Int, userId: Int, staff: CompanyStaff): BaseResponse<Any>
    suspend fun deleteCompanyStaff(staffId: Int): BaseResponse<Any>
}