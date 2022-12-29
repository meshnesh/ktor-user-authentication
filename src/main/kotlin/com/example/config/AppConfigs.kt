package com.example.config

import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.routes.authRoutes
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import com.example.db.DataBaseFactory

fun configureDatabase() {
    DataBaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.configureAuthRouting(){
    val userService: UserService = UserServiceImpl()
    val userRepository: UserRepository = UserRepositoryImpl(userService)
    authRoutes(userRepository)
}
