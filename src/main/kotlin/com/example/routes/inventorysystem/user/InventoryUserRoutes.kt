package com.example.routes.inventorysystem.user

import com.example.repository.inventorysystem.user.InventoryUserRepository
import com.example.security.UserIdPrincipalForUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUserRoutes(repository: InventoryUserRepository) {
    routing {
        authenticate {
            route("inventory-system/user") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getUser(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}
