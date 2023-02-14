package com.example

import com.example.config.configureAuthRouting
import com.example.config.configureContentNegotiation
import com.example.config.configureDatabase
import com.example.db.DataBaseFactory
import com.example.di.RepositoryProvider
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routes.products.configureProductRoutes
import com.example.routes.user.configureUserRoutes
import com.example.security.configureSecurity
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "127.0.0.1"){
//        DataBaseFactory.init()
//        configureDatabase()
//        configureContentNegotiation()
//        configureSecurity()
//        configureRouting()
//        configureAuthRouting()
//        configureUserRoutes(RepositoryProvider.provideUserRepository())
//        configureProductRoutes(RepositoryProvider.provideAvailableProductsRepository())
//
//        routing {
//            authenticate {
//                get("test/auth") {
//                    call.respond("Working fine")
//                }
//            }
//        }
//    }.start(wait = true)
//}

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureDatabase()
    configureContentNegotiation()
    configureSecurity()
    configureRouting()
    configureAuthRouting()
    configureUserRoutes(RepositoryProvider.provideUserRepository())
    configureProductRoutes(RepositoryProvider.provideAvailableProductsRepository())

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}
