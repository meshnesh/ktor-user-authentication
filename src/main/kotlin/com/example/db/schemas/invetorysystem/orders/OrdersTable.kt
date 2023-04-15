package com.example.db.schemas.invetorysystem.orders

import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import com.example.db.schemas.invetorysystem.staff.StaffTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object OrdersTable: Table("OrdersTableTest") {
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val staffId = integer("staffId").references(ref = StaffTable.staffId, onDelete = ReferenceOption.CASCADE)
    val orderId = integer("orderId").autoIncrement()
    val orderName = varchar("orderName", 50)
    val orderDateTime = varchar("orderDateTime", 50)
    val orderTotalPrice = integer("orderTotalPrice")
    val orderStatus = varchar("orderStatus", 50)
    override var primaryKey = PrimaryKey(orderId)
}