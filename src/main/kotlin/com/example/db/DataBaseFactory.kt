package com.example.db

import com.example.db.schemas.AvailableProductsTable
import com.example.db.schemas.BranchesTable
import com.example.db.schemas.PurchasedProductsTable
import com.example.db.schemas.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(UserTable)
            SchemaUtils.create(AvailableProductsTable)
            SchemaUtils.create(PurchasedProductsTable)
            SchemaUtils.create(BranchesTable)
        }
    }

    private fun hikari(): HikariDataSource{
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl= "jdbc:postgresql:testingusers?user=t&password=1234"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}