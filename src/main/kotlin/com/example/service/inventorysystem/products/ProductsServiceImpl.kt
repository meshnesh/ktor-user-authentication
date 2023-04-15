package com.example.service.inventorysystem.products

import com.example.db.DataBaseFactory.dbQuery
import com.example.db.extensions.toAvailableProducts
import com.example.db.schemas.invetorysystem.productsSchema.ProductsTable
import com.example.models.common.PaginatedResult
import com.example.models.inventorysystem.product.AvailableProductsPayload
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class ProductsServiceImpl : ProductsService {
    override suspend fun getAvailableProducts(page: Int, limit: Int): PaginatedResult<AvailableProductsPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val availableProducts = dbQuery {
            ProductsTable
                .selectAll().orderBy(ProductsTable.productName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toAvailableProducts() }
        }
        return PaginatedResult(pageCount, nextPage, availableProducts)
    }

    override suspend fun getCompanyProducts (companyId: Int, page: Int, limit: Int): PaginatedResult<AvailableProductsPayload> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val products = dbQuery {
            ProductsTable
                .select { ProductsTable.companyId eq companyId }.orderBy(ProductsTable.productName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toAvailableProducts() }
        }
        return PaginatedResult(pageCount, nextPage, products)
    }

    override suspend fun add(availableProductsPayload: AvailableProductsPayload): AvailableProductsPayload? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = ProductsTable.insert {
                it[companyId] = availableProductsPayload.companyId
                it[productName] = availableProductsPayload.productName
                it[productDescription] = availableProductsPayload.productDescription
                it[productPrice] = availableProductsPayload.productPrice
                it[productQuantity] = availableProductsPayload.productQuantity
                it[productImage] = availableProductsPayload.productImage
                it[productCode] = availableProductsPayload.productCode
            }
        }
        return statement?.resultedValues?.get(0).toAvailableProducts()
    }

    override suspend fun update(productId: Int, availableProductsPayload: AvailableProductsPayload): Boolean {
        var result = -1
        dbQuery {
            result = ProductsTable.update({ ProductsTable.productId eq productId }) {
                it[companyId] = availableProductsPayload.companyId
                it[productName] = availableProductsPayload.productName
                it[productDescription] = availableProductsPayload.productDescription
                it[productPrice] = availableProductsPayload.productPrice
                it[productQuantity] = availableProductsPayload.productQuantity
                it[productImage] = availableProductsPayload.productImage
                it[productCode] = availableProductsPayload.productCode
            }
        }
        return result == 1
    }

    override suspend fun delete(productId: Int): Boolean {
        var result = -1
        dbQuery {
            result = ProductsTable.deleteWhere { ProductsTable.productId eq productId}
        }
        return result == 1
    }
}