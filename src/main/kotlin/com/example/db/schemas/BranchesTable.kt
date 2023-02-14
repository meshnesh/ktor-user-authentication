package com.example.db.schemas

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object BranchesTable: Table("companyBranch") {
    var id = integer("id").autoIncrement()
    var branchName = varchar("branchName", 50)
    var branchLocation = varchar("branchLocation", 50)
    val userId = integer("user_id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)

    override var primaryKey = PrimaryKey(id)
}
