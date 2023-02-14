package com.example.routes.user

import com.example.repository.user.UserRepository
import com.example.security.UserIdPrincipalForUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

fun Application.configureUserRoutes(repository: UserRepository) {
    routing {
        authenticate {
            route("/user") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getUser(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}
