package com.example.db.schemas.companySchema


import com.example.db.schemas.UserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CompanyDbTable : Table("companiesTestDbTable1") {
    val companyId = integer("companyId").autoIncrement()
    val userId = integer("user_id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)
    val companyName = varchar("companyName", 30)
    val companySubscription = bool("companySubscription")
    val companySubscriptionStatus = varchar("companySubscriptionStatus", 500)
    override val primaryKey = PrimaryKey(companyId)
}
