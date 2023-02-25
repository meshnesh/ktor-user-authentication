package com.example.db.schemas.invoice

import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.customers.CustomerTable
import com.example.db.schemas.staff.StaffTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table("invoiceTable") {
    var invoiceId = integer("invoiceId").autoIncrement()
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val staffId = CustomerTable.integer("companyId")
        .references(ref = StaffTable.id, onDelete = ReferenceOption.CASCADE)
    var invoiceName = varchar("invoiceName", 50)
    var invoiceNumber = varchar("invoiceNumber", 50)
    var invoiceDescription = varchar("invoiceDescription", 50)
    var invoicePrice = integer("invoicePrice")
    var invoiceQuantity = integer("invoiceQuantity")
    var invoiceDateTime = varchar("invoiceDateTime", 50)
    var invoiceDueDateTime = varchar("invoiceDueDateTime", 50)
    var invoiceStatus = varchar("invoiceStatus", 50)
    override var primaryKey = PrimaryKey(invoiceId)
}