package com.example.service.blogs.story

import com.example.db.DataBaseFactory
import com.example.db.extensions.toAddStory
import com.example.db.extensions.toBlogUser
import com.example.db.extensions.toStory
import com.example.db.extensions.toStoryJoinedWithUser
import com.example.db.schemas.blogs.comment.CommentTable
import com.example.db.schemas.blogs.likes.LikeTable
import com.example.db.schemas.blogs.story.StoryTable
import com.example.db.schemas.blogs.users.BlogUserTable
import com.example.models.blogs.comment.Comment
import com.example.models.blogs.story.Story
import com.example.models.blogs.users.AddStoryPayload
import com.example.models.common.PaginatedResult
import com.example.routes.blogs.story.StoryParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class StoryServiceImpl : StoryService {

    override suspend fun get(id: Int): Story? {
        val storyRow = DataBaseFactory.dbQuery { StoryTable.select { StoryTable.id eq id }.first() }
        return storyRow.toStory()
    }

    override suspend fun getMyStories(userId: Int, page: Int, limit: Int): PaginatedResult<Story> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val stories = DataBaseFactory.dbQuery {
            StoryTable
                .innerJoin(BlogUserTable, { BlogUserTable.id }, { StoryTable.userId })
                .select { StoryTable.userId eq userId }.orderBy(StoryTable.createdAt, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toStoryJoinedWithUser() }
        }
        return PaginatedResult(pageCount, nextPage, stories)
    }

    override suspend fun getAllStories(page: Int, limit: Int): PaginatedResult<Story> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val stories = DataBaseFactory.dbQuery {
            StoryTable
                .innerJoin(BlogUserTable, { BlogUserTable.id }, { StoryTable.userId })
                .selectAll().orderBy(StoryTable.createdAt, SortOrder.DESC).also {
                    pageCount = it.count() / limit
                    if (page < pageCount)
                        nextPage = page + 1L
                }.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toStoryJoinedWithUser() }
        }
        return PaginatedResult(pageCount, nextPage, stories)
    }

    override suspend fun getLikedStories(userId: Int): List<Story> {
        return DataBaseFactory.dbQuery {
            val storyTable = StoryTable.alias("s")
            LikeTable.innerJoin(storyTable, { LikeTable.storyId }, { storyTable[StoryTable.id] })
                .select { LikeTable.userId eq userId }
                .mapNotNull { it.toStory() }
        }
    }

    override suspend fun add(userId: Int, storyParams: StoryParams): AddStoryPayload? {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {

            val userRow =  BlogUserTable.select { BlogUserTable.id eq userId }.first()

            statement = StoryTable.insert {
                it[this.userId] = userRow.toBlogUser()!!.id
                it[title] = storyParams.title
                it[content] = storyParams.content
                it[isDraft] = storyParams.isDraft
                it[isUpdated] = storyParams.isUpdated
            }
        }
        return statement?.resultedValues?.get(0).toAddStory()
    }

    override suspend fun update(id: Int, storyParams: Story): Boolean {
//        var result = -1
//        DataBaseFactory.dbQuery {
//            result = StoryTable.update({ StoryTable.id eq id }) {
//                it[userId] = storyParams.userId
//                it[title] = storyParams.title
//                it[content] = storyParams.content
//                it[isDraft] = storyParams.isDraft
//            }
//        }
//        return result == 1
        return TODO()
    }

    override suspend fun delete(storyId: Int): Boolean {
//        var result = -1
//        DataBaseFactory.dbQuery {
//            result = StoryTable.deleteWhere { StoryTable.id eq storyId }
//        }
//        return result == 1
        return TODO()
    }

    override suspend fun like(userId: Int, storyId: Int): Boolean {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = LikeTable.insert {
                it[this.userId] = userId
                it[this.storyId] = storyId
            }
        }
        return statement?.resultedValues?.isNotEmpty() ?: false
    }

    override suspend fun comment(userId: Int, storyId: Int, comment: String): Boolean {
        var statement: InsertStatement<Number>? = null
        DataBaseFactory.dbQuery {
            statement = CommentTable.insert {
                it[this.userId] = userId
                it[this.storyId] = storyId
                it[this.comment] = comment
            }
        }
        return statement?.resultedValues?.isNotEmpty() ?: false
    }

    override suspend fun getComments(storyId: Int): List<Comment> {
        return TODO()
//        DataBaseFactory.dbQuery {
//            CommentTable.select { CommentTable.storyId eq storyId }.mapNotNull {
//                it.toComment()
//            }
//        }
    }
}