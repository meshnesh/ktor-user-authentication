package com.example.routes.blogs.story

import com.example.models.blogs.story.Story
import com.example.repository.blogs.story.StoryRepository
import com.example.security.UserIdPrincipalForUser
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureStoryRoutes(repository: StoryRepository) {
    routing {
        authenticate {
            route("story") {
                get("my/{page}") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllStories(page, limit)
                    call.respond(result.statusCode, result)
                }

                get("all") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllStories(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add") {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val params = call.receive<StoryParams>()
                    val result = repository.add(principal?.id!!, params)
                    call.respond(result.statusCode, result)
                }

                put("update/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1
                    val params = call.receive<Story>()
                    val result = repository.update(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1
                    val result = repository.delete(id)
                    call.respond(result.statusCode, result)
                }

                get("{story_id}/comments") {
                    val storyId = call.parameters["story_id"]?.toIntOrNull() ?: -1
                    val result = repository.getComments(storyId)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}