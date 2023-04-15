package com.example.service.inventorysystem.orders

import com.example.db.DataBaseFactory
import com.example.db.extensions.toOrders
import com.example.db.schemas.invetorysystem.orders.OrdersTable
import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.orders.OrdersPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class OrdersServiceImpl : OrdersService {
    override suspend fun getAllOrders(page: Int, limit: Int): PaginatedResult<OrdersPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val order = DataBaseFactory.dbQuery {
            OrdersTable
                .selectAll().orderBy(OrdersTable.orderName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toOrders() }
        }
        return PaginatedResult(pageCount, nextPage, order)
    }

    override suspend fun createOrder(ordersPayload: OrdersPayload): OrdersPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = OrdersTable.insert {
                it[companyId] = ordersPayload.companyId
                it[staffId] = ordersPayload.staffId
                it[orderName] = ordersPayload.orderName
                it[orderDateTime] = ordersPayload.orderDateTime
                it[orderTotalPrice] = ordersPayload.orderTotalPrice
                it[orderStatus] = ordersPayload.orderStatus
            }
        }
        return statement?.resultedValues?.get(0).toOrders()
    }

    override suspend fun updateOrder(orderId: Int, ordersPayload: OrdersPayload): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = OrdersTable.update({ OrdersTable.orderId eq orderId }) {
                it[companyId] = ordersPayload.companyId
                it[staffId] = ordersPayload.staffId
                it[orderName] = ordersPayload.orderName
                it[orderDateTime] = ordersPayload.orderDateTime
                it[orderTotalPrice] = ordersPayload.orderTotalPrice
                it[orderStatus] = ordersPayload.orderStatus
            }
        }
        return result == 1
    }

    override suspend fun deleteOrder(orderId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = OrdersTable.deleteWhere { OrdersTable.orderId eq orderId }
        }
        return result == 1
    }
}