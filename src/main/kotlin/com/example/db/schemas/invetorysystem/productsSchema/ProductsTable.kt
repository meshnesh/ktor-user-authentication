package com.example.db.schemas.invetorysystem.productsSchema

import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ProductsTable : Table("ProductsTableTest1") {
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val productId = integer("productId").autoIncrement()
    val productName = varchar("productName", 30)
    val productDescription = varchar("productDescription", 256)
    val productPrice = integer("productPrice")
    val productQuantity = integer("productQuantity")
    val productImage = varchar("productImage", 256)
    val productCode = varchar("productCode", 256)
    override val primaryKey = PrimaryKey(productId)
}
