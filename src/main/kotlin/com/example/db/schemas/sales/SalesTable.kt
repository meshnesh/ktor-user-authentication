package com.example.db.schemas.sales

import org.jetbrains.exposed.sql.Table

object SalesTable : Table("salesTable") {
    var saleId = SalesTable.integer("saleId").autoIncrement()
    var saleName = SalesTable.varchar("saleName", 50)
    var saleDateTime = SalesTable.varchar("saleDateTime", 50)
    var saleTotalPrice = SalesTable.integer("saleTotalPrice")
    var saleStatus = SalesTable.varchar("saleStatus", 50)
    override var primaryKey = PrimaryKey(saleId)
}
