package com.example.service.sales

import com.example.db.DataBaseFactory
import com.example.db.extensions.toSales
import com.example.db.schemas.sales.SalesTable
import com.example.models.common.PaginatedResult
import com.example.models.sales.SalesPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class SalesServiceImpl : SalesService {
    override suspend fun getAllSales(page: Int, limit: Int): PaginatedResult<SalesPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val company = DataBaseFactory.dbQuery {
            SalesTable
                .selectAll().orderBy(SalesTable.saleName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toSales() }
        }
        return PaginatedResult(pageCount, nextPage, company)
    }

    override suspend fun createSale(salesPayload: SalesPayload): SalesPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = SalesTable.insert {
                it[companyId] = salesPayload.companyId
                it[staffId] = salesPayload.staffId
                it[orderId] = salesPayload.orderId
                it[saleName] = salesPayload.saleName
                it[saleStatus] = salesPayload.saleStatus
                it[saleDateTime] = salesPayload.saleDateTime
                it[saleTotalPrice] = salesPayload.saleTotalPrice
                it[saleStatus] = salesPayload.saleStatus
            }
        }
        return statement?.resultedValues?.get(0).toSales()
    }

    override suspend fun updateSale(saleId: Int, salesPayload: SalesPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = SalesTable.update({ SalesTable.saleId eq saleId }) {
                it[companyId] = salesPayload.companyId
                it[staffId] = salesPayload.staffId
                it[orderId] = salesPayload.orderId
                it[saleName] = salesPayload.saleName
                it[saleStatus] = salesPayload.saleStatus
                it[saleDateTime] = salesPayload.saleDateTime
                it[saleTotalPrice] = salesPayload.saleTotalPrice
                it[saleStatus] = salesPayload.saleStatus
            }
        }
        return result == 1
    }

    override suspend fun deleteSale(saleId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = SalesTable.deleteWhere { SalesTable.saleId eq saleId }
        }
        return result == 1
    }
}