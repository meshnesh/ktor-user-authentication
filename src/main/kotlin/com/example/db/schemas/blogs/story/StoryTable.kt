package com.example.db.schemas.blogs.story

import com.example.db.schemas.blogs.users.BlogUserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object StoryTable : Table("tech-blog") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(ref = BlogUserTable.id, onDelete = ReferenceOption.CASCADE)
    val title = varchar("title", 256)
    val content = text("content")
    val isDraft = bool("is_draft").clientDefault { true }
    val isUpdated = bool("is_updated").clientDefault { false }
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(id)
}