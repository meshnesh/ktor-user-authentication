package com.example.db.schemas.invetorysystem.sales

import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import com.example.db.schemas.invetorysystem.orders.OrdersTable
import com.example.db.schemas.invetorysystem.staff.StaffTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SalesTable : Table("salesTableTest") {
    var saleId = SalesTable.integer("saleId").autoIncrement()
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val staffId = integer("staffId").references(ref = StaffTable.staffId, onDelete = ReferenceOption.CASCADE)
    val orderId = integer("orderId").references(ref = OrdersTable.orderId, onDelete = ReferenceOption.CASCADE)
    var saleName = SalesTable.varchar("saleName", 50)
    var saleDateTime = SalesTable.varchar("saleDateTime", 50)
    var saleTotalPrice = SalesTable.integer("saleTotalPrice")
    var saleStatus = SalesTable.varchar("saleStatus", 50)
    override var primaryKey = PrimaryKey(saleId)
}
