package com.example.service.inventorysystem.company.companyBranch

import com.example.db.DataBaseFactory
import com.example.db.extensions.toCompanyBranch
import com.example.db.schemas.invetorysystem.companySchema.CompanyBranchTable
import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.company.companyBranch.CompanyBranchPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class CompanyBranchServiceImpl : CompanyBranchService {

    override suspend fun createCompanyBranch(params: CompanyBranchPayload): CompanyBranchPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = CompanyBranchTable.insert {
                it[companyId] = params.companyId
                it[branchName] = params.branchName
                it[branchLocation] = params.branchLocation
            }
        }
        return statement?.resultedValues?.get(0).toCompanyBranch()
    }

    override suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranchPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CompanyBranchTable.update({ CompanyBranchTable.branchId eq id }) {
                it[companyId] = companyBranch.companyId
                it[branchName] = companyBranch.branchName
                it[branchLocation] = companyBranch.branchLocation
            }
        }
        return result == 1
    }

    override suspend fun deleteCompanyBranch(id: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CompanyBranchTable.deleteWhere { branchId eq id }
        }
        return result == 1
    }

    override suspend fun fetchAllCompanyBranch(page: Int, limit: Int): PaginatedResult<CompanyBranchPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val companyBranch = DataBaseFactory.dbQuery {
            CompanyBranchTable
                .selectAll().orderBy(CompanyBranchTable.branchName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toCompanyBranch() }
        }
        return PaginatedResult(pageCount, nextPage, companyBranch)
    }
}