package com.example.routes

import com.example.repository.user.UserRepository
import com.example.models.CreateUserParams
import com.example.models.UserLoginParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(userRepository: UserRepository) {
    routing {
        route("/auth") {
            post ( "/register" ) {
                val params = call.receive<CreateUserParams>()
                val result = userRepository.registerUser(params)
                call.respond(result)
            }

            post("/login") {
                val params = call.receive<UserLoginParams>()
                val result = userRepository.loginUser(params)
                call.respond(result) //result.statusCode, to be added when we switch back to json response
            }
        }
    }
}
