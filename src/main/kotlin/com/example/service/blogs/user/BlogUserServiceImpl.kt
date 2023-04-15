package com.example.service.blogs.user

import com.example.db.DataBaseFactory.dbQuery
import com.example.db.extensions.toBlogUser
import com.example.db.schemas.blogs.users.BlogUserTable
import com.example.models.blogs.users.BlogUser
import com.example.models.blogs.users.RegisterBlogUser
import com.example.security.hash
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class BlogUserServiceImpl : BlogUserService {
    override suspend fun registerUser(params: RegisterBlogUser): BlogUser? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = BlogUserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[email] = params.email
                it[avatar] = params.avatar
            }
        }

        return statement?.resultedValues?.get(0).toBlogUser()
    }

    override suspend fun loginUser(email: String, password: String): BlogUser? {
        val hashedPassword = hash(password)
        val userRow = dbQuery { BlogUserTable.select { BlogUserTable.email eq email and (BlogUserTable.password eq hashedPassword) }.firstOrNull() }
        return userRow.toBlogUser()
    }

    override suspend fun findUserByEmail(email: String): BlogUser? {
        val user = dbQuery {
            BlogUserTable.select { BlogUserTable.email.eq(email) }
                .map { it.toBlogUser() }.singleOrNull()
        }
        return user
    }
}