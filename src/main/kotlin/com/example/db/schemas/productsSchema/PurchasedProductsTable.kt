package com.example.db.schemas.productsSchema

import com.example.db.schemas.UserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object PurchasedProductsTable : Table("purchasedProducts") {
    val productId = integer("id").autoIncrement()
    val productName = varchar("productName", 30)
    val productDescription = varchar("productDescription", 256)
    val productPrice = varchar("productPrice", 20)
    val productQuantity = varchar("productQuantity", 20000)
    val userId = integer("user_id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)
}
