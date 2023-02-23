package com.example.db.schemas.customers

import org.jetbrains.exposed.sql.Table

object CustomerTable : Table("customerTable") {
    var customerId = integer("customerId").autoIncrement()
    var customerName = varchar("customerName", 50)
    var customerPhoneNumber = varchar("customerPhoneNumber", 50)
    var customerAddress = varchar("customerAddress", 50)
    override var primaryKey = PrimaryKey(customerId)
}