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
//        config.jdbcUrl= "jdbc:postgresql:testingusers?user=t&password=1234"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        config.jdbcUrl =
            "jdbc:postgres://knjhdahpscmuxl:51b46fd9f307eda51a2340dd297b4fced1431ff2511893c632534928c87288cd@ec2-44-199-143-43.compute-1.amazonaws.com:5432/d5ovidmele2ehi"



        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}