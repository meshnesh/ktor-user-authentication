package com.example

import com.example.config.configureAuthRouting
import com.example.config.configureContentNegotiation
import com.example.config.configureDatabase
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.security.configureSecurity
import io.ktor.server.plugins.statuspages.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1"){
//        DataBaseFactory.init()
        configureDatabase()
        configureContentNegotiation()
        configureSecurity()
        configureRouting()
        configureAuthRouting()
    }.start(wait = true)
}

//fun Application.module() {
//
//
//}
