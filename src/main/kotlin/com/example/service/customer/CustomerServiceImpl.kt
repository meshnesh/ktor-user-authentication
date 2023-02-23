package com.example.service.customer

import com.example.db.DataBaseFactory
import com.example.db.extensions.toCustomer
import com.example.db.schemas.customers.CustomerTable
import com.example.models.common.PaginatedResult
import com.example.models.customers.CustomerPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class CustomerServiceImpl : CustomerService {
    override suspend fun getAllCustomers(page: Int, limit: Int): PaginatedResult<CustomerPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val company = DataBaseFactory.dbQuery {
            CustomerTable
                .selectAll().orderBy(CustomerTable.customerName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toCustomer() }
        }
        return PaginatedResult(pageCount, nextPage, company)
    }

    override suspend fun createCustomer(customerPayload: CustomerPayload): CustomerPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = CustomerTable.insert {
                it[customerName] = customerPayload.customerName
                it[customerPhoneNumber] = customerPayload.customerPhoneNumber
                it[customerAddress] = customerAddress
            }
        }
        return statement?.resultedValues?.get(0).toCustomer()
    }

    override suspend fun updateCustomer(customerId: Int, customerPayload: CustomerPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CustomerTable.update({ CustomerTable.customerId eq customerId }) {
                it[customerName] = customerPayload.customerName
                it[customerPhoneNumber] = customerPayload.customerPhoneNumber
                it[customerAddress] = customerAddress
            }
        }
        return result == 1
    }

    override suspend fun deleteCustomer(customerId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = CustomerTable.deleteWhere { CustomerTable.customerId eq customerId }
        }
        return result == 1
    }
}