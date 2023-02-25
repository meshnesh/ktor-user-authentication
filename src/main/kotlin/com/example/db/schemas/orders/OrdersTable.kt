package com.example.db.schemas.orders

import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.customers.CustomerTable
import com.example.db.schemas.staff.StaffTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object OrdersTable: Table("OrdersTable") {
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val staffId = CustomerTable.integer("companyId")
        .references(ref = StaffTable.id, onDelete = ReferenceOption.CASCADE)
    var orderId = integer("orderId").autoIncrement()
    var orderName = varchar("orderName", 50)
    var orderDateTime = varchar("orderDateTime", 50)
    var orderTotalPrice = integer("orderTotalPrice")
    var orderStatus = varchar("orderStatus", 50)
    override var primaryKey = PrimaryKey(orderId)
}