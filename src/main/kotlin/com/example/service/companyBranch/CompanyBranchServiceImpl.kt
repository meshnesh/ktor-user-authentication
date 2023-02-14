package com.example.service.companyBranch

import com.example.db.DataBaseFactory
import com.example.db.extensions.toAvailableProducts
import com.example.db.extensions.toCompanyBranch
import com.example.db.schemas.AvailableProductsTable
import com.example.db.schemas.BranchesTable
import com.example.models.common.PaginatedResult
import com.example.models.companyBranch.CompanyBranch
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class CompanyBranchServiceImpl : CompanyBranchService {

    override suspend fun createCompanyBranch(params: CompanyBranch): CompanyBranch? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = BranchesTable.insert {
                it[branchName] = params.branchName
                it[branchLocation] = params.branchLocation
                it[userId] = params.userId
            }
        }
        return statement?.resultedValues?.get(0).toCompanyBranch()
    }

    override suspend fun editCompanyBranch(id: Int, companyBranch: CompanyBranch): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = BranchesTable.update({ BranchesTable.id eq id }) {
                it[branchName] = companyBranch.branchName
                it[branchLocation] = companyBranch.branchLocation
                it[userId] = companyBranch.userId
            }
        }
        return result == 1
    }

    override suspend fun deleteCompanyBranch(id: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = BranchesTable.deleteWhere {BranchesTable.id eq id}
        }
        return result == 1
    }

    override suspend fun fetchAllCompanyBranch(page: Int, limit: Int): PaginatedResult<CompanyBranch> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val companyBranch = DataBaseFactory.dbQuery {
            BranchesTable
                .selectAll().orderBy(BranchesTable.branchName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toCompanyBranch() }
        }
        return PaginatedResult(pageCount, nextPage, companyBranch)
    }
}