package com.example.db.schemas.companySchema


import org.jetbrains.exposed.sql.Table

object CompanyTable : Table("companyTable") {
    val companyId = integer("companyId").autoIncrement()
    val companyName = varchar("companyName", 30)
    val companySubscription = bool("companySubscription")
    val companySubscriptionStatus = varchar("companySubscriptionStatus", 30)
    override val primaryKey = PrimaryKey(CompanyTable.companyId)
}
