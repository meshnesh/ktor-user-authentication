package com.example.service.products

import com.example.db.DataBaseFactory
import com.example.db.DataBaseFactory.dbQuery
import com.example.db.schemas.AvailableProductsTable
import com.example.db.extensions.toAvailableProducts
import com.example.models.common.PaginatedResult
import com.example.models.product.AvailableProducts
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class AvailableProductsServiceImpl : AvailableProductsService {
    override suspend fun getAvailableProducts(page: Int, limit: Int): PaginatedResult<AvailableProducts> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val availableProducts = dbQuery {
            AvailableProductsTable
                .selectAll().orderBy(AvailableProductsTable.productName, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toAvailableProducts() }
        }
        return PaginatedResult(pageCount, nextPage, availableProducts)
    }

    override suspend fun add(availableProducts: AvailableProducts): AvailableProducts? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = AvailableProductsTable.insert {
                it[productName] = availableProducts.productName
                it[productDescription] = availableProducts.productDescription
                it[productPrice] = availableProducts.productPrice
                it[productQuantity] = availableProducts.productQuantity
            }
        }
        return statement?.resultedValues?.get(0).toAvailableProducts()
    }

    override suspend fun update(id: Int, availableProducts: AvailableProducts): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = AvailableProductsTable.update({ AvailableProductsTable.productId eq id }) {
                it[productName] = availableProducts.productName
                it[productDescription] = availableProducts.productDescription
                it[productPrice] = availableProducts.productPrice
                it[productQuantity] = availableProducts.productQuantity
            }
        }
        return result == 1
    }

    override suspend fun delete(productId: Int): Boolean {
        var result = -1
        DataBaseFactory.dbQuery {
            result = AvailableProductsTable.deleteWhere {AvailableProductsTable.productId eq productId}
        }
        return result == 1
    }
}