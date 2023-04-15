package com.example.routes

import com.example.models.inventorysystem.auth.CreateUserParams
import com.example.models.inventorysystem.auth.UserLoginParams
import com.example.repository.inventorysystem.auth.AuthRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(authRepository: AuthRepository) {
    routing {
        route("inventory-system/auth") {
            post ( "/register" ) {
                val params = call.receive<CreateUserParams>()
                val result = authRepository.registerUser(params)
                call.respond(result.statusCode, result)
            }

            post("/login") {
                val params = call.receive<UserLoginParams>()
                val result = authRepository.loginUser(params)
                call.respond(result.statusCode, result)
            }

            //needs token to verify user can crete more users
            post("/create-user") {
                val params = call.receive<CreateUserParams>()
                val result = authRepository.createUser("role", params)
                call.respond(result.statusCode, result)
            }
        }
    }
}
