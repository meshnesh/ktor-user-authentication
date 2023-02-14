package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

object AvailableProductsTable: Table("availableProducts") {
    val productId = integer("productId").autoIncrement()
    val productName = varchar("productName", 30)
    val productDescription = varchar("productDescription", 256)
    val productPrice= integer("productPrice")
    val productQuantity= integer("productQuantity")
    override val primaryKey = PrimaryKey(productId)
}
