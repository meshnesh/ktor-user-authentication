package com.example.db.schemas.orders

import org.jetbrains.exposed.sql.Table

object OrdersTable: Table("OrdersTable") {
    var orderId = integer("orderId").autoIncrement()
    var orderName = varchar("orderName", 50)
    var orderDateTime = varchar("orderDateTime", 50)
    var orderTotalPrice = integer("orderTotalPrice")
    var orderStatus = varchar("orderStatus", 50)
    override var primaryKey = PrimaryKey(orderId)
}