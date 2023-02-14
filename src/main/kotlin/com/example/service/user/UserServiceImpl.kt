package com.example.service.user

import com.example.db.DataBaseFactory.dbQuery
import com.example.db.schemas.UserTable
import com.example.db.extensions.toUser
import com.example.models.user.User
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }
}