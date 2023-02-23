package com.example

import com.example.config.configureAuthRouting
import com.example.config.configureContentNegotiation
import com.example.config.configureDatabase
import com.example.di.RepositoryProvider
import com.example.plugins.configureRouting
import com.example.routes.company.configureCompanyRoutes
import com.example.routes.customer.configureCustomerRoutes
import com.example.routes.invoice.configureInvoiceRoutes
import com.example.routes.order.configureOrderRoutes
import com.example.routes.products.configureProductRoutes
import com.example.routes.sale.configureSalesRoutes
import com.example.routes.user.configureUserRoutes
import com.example.security.configureSecurity
import io.ktor.server.application.*
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
//configureCompanyRoutes(RepositoryProvider.provideCompanyRepository())
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
    configureProductRoutes(RepositoryProvider.provideProductsRepository())
    configureCompanyRoutes(RepositoryProvider.provideCompanyRepository())
    configureCustomerRoutes(RepositoryProvider.provideCustomerRepository())
    configureInvoiceRoutes(RepositoryProvider.provideInvoiceRepository())
    configureOrderRoutes(RepositoryProvider.provideOrdersRepository())
    configureSalesRoutes(RepositoryProvider.provideSalesRepository())

    routing {
        get("/") {
            call.respondText("Testing this")
        }
    }
}
