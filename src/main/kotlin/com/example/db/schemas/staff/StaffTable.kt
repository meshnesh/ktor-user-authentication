package com.example.db.schemas.staff

import com.example.db.schemas.UserTable
import com.example.db.schemas.companySchema.CompanyDbTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object StaffTable : Table("staffTableTestTest2") {

    var staffId = integer("staffId").autoIncrement()
    val companyId = integer("companyId").references(ref = CompanyDbTable.companyId, onDelete = ReferenceOption.CASCADE)
    val userId = integer("id").references(ref = UserTable.id, onDelete = ReferenceOption.CASCADE)
    var staffIdNo = varchar("staffIdNo", 20)
    var staffFirstName = varchar("staffFirstName", 256)
    var staffLastName = varchar("staffLastName", 256)
    var staffAvatar = text("staffAvatar")
    var staffEmail = varchar("staffEmail", 256)
    var staffPassword = text("staffPassword")
    var staffCreatedAt = datetime("staffCreatedAt").clientDefault { LocalDateTime.now() }
    var staffJoinDate = varchar("staffJoinDate", 256)
    var staffIsAdmin = bool("staffIsAdmin").default(false)
    var staffRole = varchar("staffRole", 20)

    override var primaryKey = PrimaryKey(staffId)
}