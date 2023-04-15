package com.example.service.inventorysystem.invoice

import com.example.db.DataBaseFactory
import com.example.db.extensions.toInvoice
import com.example.db.schemas.invetorysystem.invoice.InvoiceTable
import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.invoices.InvoicePayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class InvoiceServiceImpl : InvoiceService {
    override suspend fun getAllInvoices(page: Int, limit: Int): PaginatedResult<InvoicePayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val invoice = DataBaseFactory.dbQuery {
            InvoiceTable
                .selectAll().orderBy(InvoiceTable.invoiceName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toInvoice() }
        }
        return PaginatedResult(pageCount, nextPage, invoice)
    }

    override suspend fun createInvoice(invoicePayload: InvoicePayload): InvoicePayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = InvoiceTable.insert {
                it[companyId] = invoicePayload.companyId
                it[staffId] = invoicePayload.staffId
                it[invoiceName] = invoicePayload.invoiceName
                it[invoiceNumber] = invoicePayload.invoiceNumber
                it[invoiceDescription] = invoicePayload.invoiceDescription
                it[invoicePrice] = invoicePayload.invoicePrice
                it[invoiceQuantity] = invoicePayload.invoiceQuantity
                it[invoiceDateTime] = invoicePayload.invoiceDateTime
                it[invoiceDueDateTime] = invoicePayload.invoiceDueDateTime
                it[invoiceStatus] = invoicePayload.invoiceStatus
            }
        }
        return statement?.resultedValues?.get(0).toInvoice()
    }

    override suspend fun updateInvoice(invoiceId: Int, invoicePayload: InvoicePayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = InvoiceTable.update({ InvoiceTable.invoiceId eq invoiceId }) {
                it[companyId] = invoicePayload.companyId
                it[staffId] = invoicePayload.staffId
                it[invoiceName] = invoicePayload.invoiceName
                it[invoiceNumber] = invoicePayload.invoiceNumber
                it[invoiceDescription] = invoicePayload.invoiceDescription
                it[invoicePrice] = invoicePayload.invoicePrice
                it[invoiceQuantity] = invoicePayload.invoiceQuantity
                it[invoiceDateTime] = invoicePayload.invoiceDateTime
                it[invoiceDueDateTime] = invoicePayload.invoiceDueDateTime
                it[invoiceStatus] = invoicePayload.invoiceStatus
            }
        }
        return result == 1
    }

    override suspend fun deleteInvoice(invoiceId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = InvoiceTable.deleteWhere { InvoiceTable.invoiceId eq invoiceId }
        }
        return result == 1
    }
}