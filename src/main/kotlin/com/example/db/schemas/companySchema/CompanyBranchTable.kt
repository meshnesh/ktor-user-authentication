package com.example.db.schemas.companySchema

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CompanyBranchTable : Table("companyBranchTable") {
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    var branchId = integer("id").autoIncrement()
    var branchName = varchar("branchName", 50)
    var branchLocation = varchar("branchLocation", 50)
    override var primaryKey = PrimaryKey(branchId)
}
