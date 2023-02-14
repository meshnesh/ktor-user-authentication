package com.example.config

import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.routes.authRoutes
import com.example.service.auth.AuthService
import com.example.service.auth.AuthServiceImpl
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import com.example.db.DataBaseFactory
import com.example.repository.products.AvailableProductsRepository
import com.example.routes.products.configureProductRoutes
import com.example.routes.user.configureUserRoutes
import com.example.service.products.AvailableProductsService
import com.example.service.products.AvailableProductsServiceImpl
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.channels.produce

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
