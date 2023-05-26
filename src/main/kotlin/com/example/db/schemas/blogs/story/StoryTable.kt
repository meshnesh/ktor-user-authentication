package com.example.db.schemas.blogs.story

import com.example.db.schemas.blogs.users.BlogUserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object StoryTable : Table("test-techie-blog") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(ref = BlogUserTable.id, onDelete = ReferenceOption.CASCADE)
    val title = varchar("title", 256)
    val shortDescription = text("shortDescription")
    val content = text("content")
    val coverImgUrl = text("coverImgUrl")
    val category = text("category")
    val isDraft = bool("is_draft").clientDefault { true }
    val isUpdated = bool("is_updated").clientDefault { false }
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(id)
}