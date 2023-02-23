package com.example.config

import com.example.db.DataBaseFactory
import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.routes.authRoutes
import com.example.service.auth.AuthService
import com.example.service.auth.AuthServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun configureDatabase() {
    DataBaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}

fun Application.configureAuthRouting() {
    val authService: AuthService = AuthServiceImpl()
    val authRepository: AuthRepository = AuthRepositoryImpl(authService)
    authRoutes(authRepository)
}
