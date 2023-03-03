package com.example.db.schemas.customers

import com.example.db.schemas.companySchema.CompanyDbTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CustomerTable : Table("companyCustomerTableTest1") {
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    var customerId = integer("customerId").autoIncrement()
    var customerName = varchar("customerName", 50)
    var customerPhoneNumber = varchar("customerPhoneNumber", 50)
    var customerAddress = varchar("customerAddress", 50)
    override var primaryKey = PrimaryKey(customerId)
}
