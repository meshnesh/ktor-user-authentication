package com.example.db.schemas.staff

import com.example.db.schemas.UserTable
import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.customers.CustomerTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object StaffTable : Table("staffTableTest") {

    val companyId = CustomerTable.integer("companyId")
        .references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val userId = integer("user_id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)
    var id = integer("id").autoIncrement()
    var idNo = varchar("idNo", 20)
    var firstName = varchar("firstName", 256)
    var lastName = varchar("lastName", 256)
    var avatar = text("avatar")
    var email = varchar("email", 256)
    var password = text("password")
    var createdAt = datetime("createdAt").clientDefault { LocalDateTime.now() }
    var joinDate = varchar("joinDate", 256)
    var isAdmin = bool("isAdmin").default(false)
    var role = varchar("role", 20)

    override var primaryKey = PrimaryKey(id)
}