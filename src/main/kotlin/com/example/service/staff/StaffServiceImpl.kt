package com.example.service.staff

import com.example.db.DataBaseFactory
import com.example.db.extensions.toStaff
import com.example.db.schemas.UserTable
import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.staff.StaffTable
import com.example.models.common.PaginatedResult
import com.example.models.staff.CompanyStaff
import com.example.security.hash
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class StaffServiceImpl : StaffService {
    override suspend fun getAllStaff(page: Int, limit: Int): PaginatedResult<CompanyStaff> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val staff = DataBaseFactory.dbQuery {
            StaffTable
                .selectAll().orderBy(StaffTable.firstName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toStaff() }
        }
        return PaginatedResult(pageCount, nextPage, staff)
    }

    override suspend fun createCompanyStaff(userId: Int, staff: CompanyStaff): CompanyStaff? {
        var statement: InsertStatement<Number>? = null

        DataBaseFactory.dbQuery {
            val userRow =  UserTable.select { UserTable.id eq userId }.first()

            statement = StaffTable.insert {
                it[this.userId] = userRow.toStaff()!!.id
                it[companyId] = staff.companyId
                it[email] = staff.email
                it[password] = hash(staff.password)
                it[firstName] = staff.firstName
                it[lastName] = staff.lastName
                it[UserTable.joinDate] = staff.joinDate
                it[UserTable.isAdmin] = staff.isAdmin
                it[UserTable.role] = staff.role
                it[UserTable.idNo] = staff.idNo
                it[UserTable.avatar] = staff.avatar
            }
        }
        return statement?.resultedValues?.get(0).toStaff()
    }

    override suspend fun updateCompanyStaff(staffId: Int, userId: Int, staff: CompanyStaff): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            val userRow =  UserTable.select { UserTable.id eq userId }.first()

            result = StaffTable.update({ StaffTable.id eq staffId }) {
                it[this.userId] = userRow.toStaff()!!.id
                it[companyId] = staff.companyId
                it[email] = staff.email
                it[password] = hash(staff.password)
                it[firstName] = staff.firstName
                it[lastName] = staff.lastName
                it[joinDate] = staff.joinDate
                it[isAdmin] = staff.isAdmin
                it[role] = staff.role
                it[idNo] = staff.idNo
                it[avatar] = staff.avatar
            }
        }
        return result == 1
    }

    override suspend fun deleteCompanyStaff(staffId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CompanyDbTable.deleteWhere { companyId eq companyId}
        }
        return result == 1
    }
}