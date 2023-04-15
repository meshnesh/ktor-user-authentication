package com.example.db.schemas.invetorysystem.companySchema


import com.example.db.schemas.invetorysystem.users.InvetoryUserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CompanyDbTable : Table("companiesTestDbTableTest1") {
    val companyId = integer("companyId").autoIncrement()
    val userId = integer("user_id").references(ref = InvetoryUserTable.id, onDelete = ReferenceOption.CASCADE)
    val companyName = varchar("companyName", 30)
    val companySubscription = bool("companySubscription").clientDefault { false }
    val companySubscriptionStatus = varchar("companySubscriptionStatus", 500)
    override val primaryKey = PrimaryKey(companyId)
}
