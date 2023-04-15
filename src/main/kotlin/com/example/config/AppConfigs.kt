package com.example.config

import com.example.db.DataBaseFactory
import com.example.repository.inventorysystem.auth.AuthRepository
import com.example.repository.inventorysystem.auth.AuthRepositoryImpl
import com.example.routes.authRoutes
import com.example.service.inventorysystem.auth.AuthService
import com.example.service.inventorysystem.auth.AuthServiceImpl
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
    val blogUserService: AuthService = AuthServiceImpl()
    val authRepository: AuthRepository = AuthRepositoryImpl(blogUserService)
    authRoutes(authRepository)
}
