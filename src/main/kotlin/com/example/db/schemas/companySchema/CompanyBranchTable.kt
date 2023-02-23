package com.example.db.schemas.companySchema

import org.jetbrains.exposed.sql.Table

object CompanyBranchTable : Table("companyBranchTable") {
    var branchId = integer("id").autoIncrement()
    var branchName = varchar("branchName", 50)
    var branchLocation = varchar("branchLocation", 50)
    override var primaryKey = PrimaryKey(branchId)
}
