package com.example.db.schemas.blogs.comment

import com.example.db.schemas.blogs.story.StoryTable
import com.example.db.schemas.blogs.users.BlogUserTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object CommentTable : Table("comments") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(ref = BlogUserTable.id, onDelete = ReferenceOption.CASCADE)
    val storyId = integer("story_id").references(ref = StoryTable.id, onDelete = ReferenceOption.CASCADE)
    val comment = text("comment")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(StoryTable.id)
}