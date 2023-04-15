package com.example.service.inventorysystem.company

import com.example.db.DataBaseFactory
import com.example.db.extensions.toCompany
import com.example.db.extensions.toStaff
import com.example.db.extensions.toUser
import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import com.example.db.schemas.invetorysystem.users.InvetoryUserTable
import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.company.CompanyPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class CompanyServiceImpl : CompanyService {
    override suspend fun getAllCompanies(page: Int, limit: Int): PaginatedResult<CompanyPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val company = DataBaseFactory.dbQuery {
            CompanyDbTable
                .selectAll().orderBy(CompanyDbTable.companyName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toCompany() }
        }
        return PaginatedResult(pageCount, nextPage, company)
    }

    override suspend fun createCompany(userId: Int, companyPayload: CompanyPayload): CompanyPayload? {
        var statement: InsertStatement<Number>? = null

        DataBaseFactory.dbQuery {
            val userRow =  InvetoryUserTable.select { InvetoryUserTable.id eq userId }.first()

            statement = CompanyDbTable.insert {
                it[this.userId] = userRow.toUser()!!.id
                it[companyName] = companyPayload.companyName
                it[companySubscription] = companyPayload.companySubscription
                it[companySubscriptionStatus] = companyPayload.companySubscriptionStatus
            }
        }
        return statement?.resultedValues?.get(0).toCompany()
    }

    override suspend fun updateCompany(companyId: Int, userId: Int, companyPayload: CompanyPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            val userRow =  InvetoryUserTable.select { InvetoryUserTable.id eq userId }.first()

            result = CompanyDbTable.update({ CompanyDbTable.companyId eq companyId }) {
                it[this.userId] = userRow.toStaff()!!.userId
                it[companyName] = companyPayload.companyName
                it[companySubscription] = companyPayload.companySubscription
                it[companySubscriptionStatus] = companyPayload.companySubscriptionStatus
            }
        }
        return result == 1
    }

    override suspend fun deleteCompany(companyId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CompanyDbTable.deleteWhere { CompanyDbTable.companyId eq companyId}
        }
        return result == 1
    }
}
