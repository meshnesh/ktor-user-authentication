package com.example.db.schemas.sales

import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.customers.CustomerTable
import com.example.db.schemas.staff.StaffTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SalesTable : Table("salesTable") {
    var saleId = SalesTable.integer("saleId").autoIncrement()
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val staffId = CustomerTable.integer("companyId")
        .references(ref = StaffTable.id, onDelete = ReferenceOption.CASCADE)
    var saleName = SalesTable.varchar("saleName", 50)
    var saleDateTime = SalesTable.varchar("saleDateTime", 50)
    var saleTotalPrice = SalesTable.integer("saleTotalPrice")
    var saleStatus = SalesTable.varchar("saleStatus", 50)
    override var primaryKey = PrimaryKey(saleId)
}
