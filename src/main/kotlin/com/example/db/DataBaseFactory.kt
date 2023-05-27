package com.example.db

import com.example.db.schemas.blogs.comment.CommentTable
import com.example.db.schemas.blogs.likes.LikeTable
import com.example.db.schemas.blogs.story.StoryTable
import com.example.db.schemas.blogs.users.BlogUserTable
import com.example.db.schemas.invetorysystem.companySchema.CompanyBranchTable
import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import com.example.db.schemas.invetorysystem.customers.CustomerTable
import com.example.db.schemas.invetorysystem.invoice.InvoiceTable
import com.example.db.schemas.invetorysystem.orders.OrdersTable
import com.example.db.schemas.invetorysystem.productsSchema.ProductsTable
import com.example.db.schemas.invetorysystem.productsSchema.PurchasedProductsTable
import com.example.db.schemas.invetorysystem.sales.SalesTable
import com.example.db.schemas.invetorysystem.users.InvetoryUserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

object DataBaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(InvetoryUserTable)
            SchemaUtils.create(ProductsTable)
            SchemaUtils.create(PurchasedProductsTable)
            SchemaUtils.create(CompanyDbTable)
            SchemaUtils.create(CompanyBranchTable)
            SchemaUtils.create(CustomerTable)
            SchemaUtils.create(InvoiceTable)
            SchemaUtils.create(OrdersTable)
            SchemaUtils.create(SalesTable)
            SchemaUtils.create(BlogUserTable)
            SchemaUtils.create(CommentTable)
            SchemaUtils.create(LikeTable)
            SchemaUtils.create(StoryTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        val uri = URI(System.getenv("DATABASE_URL"))
        val username = uri.userInfo.split(":").toTypedArray()[0]
        val password = uri.userInfo.split(":").toTypedArray()[1]

        config.driverClassName = System.getenv("JDBC_DRIVER")
//        config.jdbcUrl = System.getenv("LOCAL_DB_URL") //when pushing to main remove this
        config.jdbcUrl = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require" + "&user=$username&password=$password"  //when pushing to main change this

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
