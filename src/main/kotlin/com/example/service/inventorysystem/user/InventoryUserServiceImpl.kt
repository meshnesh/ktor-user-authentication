package com.example.service.inventorysystem.user

import com.example.db.DataBaseFactory.dbQuery
import com.example.db.extensions.toUser
import com.example.db.schemas.invetorysystem.users.InvetoryUserTable
import com.example.models.inventorysystem.user.InventoryUser
import org.jetbrains.exposed.sql.select

class InventoryUserServiceImpl : InventoryUserService {
    override suspend fun getUser(id: Int): InventoryUser {
        val userRow = dbQuery { InvetoryUserTable.select { InvetoryUserTable.id eq id }.first() }
        return userRow.toUser()!!
    }
}