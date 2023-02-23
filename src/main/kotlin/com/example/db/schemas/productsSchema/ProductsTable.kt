package com.example.db.schemas.productsSchema

import org.jetbrains.exposed.sql.Table

object ProductsTable : Table("ProductsTable") {
    val productId = integer("productId").autoIncrement()
    val productName = varchar("productName", 30)
    val productDescription = varchar("productDescription", 256)
    val productPrice = integer("productPrice")
    val productQuantity = integer("productQuantity")
    val productImage = varchar("productImage", 256)
    val productCode = varchar("productCode", 256)
    override val primaryKey = PrimaryKey(productId)
}
