package com.example.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("usersAuthTest1") {
    var id = integer("id").autoIncrement()
    var idNo = varchar("idNo", 20)
    var fullName = varchar("fullName", 256)
    var avatar = text("avatar")
    var email = varchar("email", 256)
    var password = text("password")
    var createdAt = datetime("createdAt").clientDefault { LocalDateTime.now() }
    var joinDate = varchar("joinDate", 256)
    var isAdmin = bool("isAdmin").default(false)
    var role = varchar("role", 20)

    override var primaryKey = PrimaryKey(id)
}
