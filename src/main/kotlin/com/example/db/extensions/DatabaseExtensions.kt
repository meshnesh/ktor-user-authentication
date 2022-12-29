package com.example.db.extensions

import com.example.db.UserTable
import com.example.models.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        avatar = this[UserTable.avatar],
        email = this[UserTable.email],
        createdAt = this[UserTable.createdAt].toString(),
        idNo = this[UserTable.idNo],
        isAdmin = this[UserTable.isAdmin],
        role = this[UserTable.role],
        joinDate = this[UserTable.joinDate]
    )
}
