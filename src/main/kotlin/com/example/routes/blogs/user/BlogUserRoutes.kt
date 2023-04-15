package com.example.routes.blogs.user

import com.example.models.blogs.users.LoginBlogUser
import com.example.models.blogs.users.RegisterBlogUser
import com.example.repository.blogs.user.BlogUserRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureBlogUserRoutes(blogUserRepository: BlogUserRepository) {
    routing {
        route("story/auth") {
            post ( "/register" ) {
                val params = call.receive<RegisterBlogUser>()
                val result = blogUserRepository.registerUser(params)
                call.respond(result.statusCode, result)
            }

            post("/login") {
                val params = call.receive<LoginBlogUser>()
                val result = blogUserRepository.loginUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}
