package com.example.service.inventorysystem.staff

import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.staff.CompanyStaff

interface StaffService {
    suspend fun getAllStaff(page: Int, limit: Int): PaginatedResult<CompanyStaff>
    suspend fun createCompanyStaff(userId: Int, staff: CompanyStaff): CompanyStaff?
    suspend fun updateCompanyStaff(staffId: Int, userId: Int, staff: CompanyStaff): Boolean
    suspend fun deleteCompanyStaff(staffId: Int): Boolean
}