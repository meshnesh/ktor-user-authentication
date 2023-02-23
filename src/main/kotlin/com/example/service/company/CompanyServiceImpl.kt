package com.example.service.company

import com.example.db.DataBaseFactory
import com.example.db.extensions.toCompany
import com.example.db.schemas.companySchema.CompanyTable
import com.example.models.common.PaginatedResult
import com.example.models.company.CompanyPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class CompanyServiceImpl : CompanyService {
    override suspend fun getAllCompanies(page: Int, limit: Int): PaginatedResult<CompanyPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val company = DataBaseFactory.dbQuery {
            CompanyTable
                .selectAll().orderBy(CompanyTable.companyName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toCompany() }
        }
        return PaginatedResult(pageCount, nextPage, company)
    }

    override suspend fun createCompany(companyPayload: CompanyPayload): CompanyPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = CompanyTable.insert {
                it[companyName] = companyPayload.companyName
                it[companySubscription] = companyPayload.companySubscription
                it[companySubscriptionStatus] = companyPayload.companySubscriptionStatus
            }
        }
        return statement?.resultedValues?.get(0).toCompany()
    }

    override suspend fun updateCompany(companyId: Int, companyPayload: CompanyPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CompanyTable.update({ CompanyTable.companyId eq companyId }) {
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
            result = CompanyTable.deleteWhere { CompanyTable.companyId eq companyId}
        }
        return result == 1
    }
}
