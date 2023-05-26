package com.example

import com.example.config.configureAuthRouting
import com.example.config.configureContentNegotiation
import com.example.config.configureDatabase
import com.example.di.RepositoryProvider
import com.example.plugins.configureRouting
import com.example.routes.blogs.story.configureStoryRoutes
import com.example.routes.blogs.user.configureBlogUserRoutes
import com.example.routes.inventorysystem.company.configureCompanyRoutes
import com.example.routes.inventorysystem.customer.configureCustomerRoutes
import com.example.routes.inventorysystem.invoice.configureInvoiceRoutes
import com.example.routes.inventorysystem.order.configureOrderRoutes
import com.example.routes.inventorysystem.products.configureProductRoutes
import com.example.routes.inventorysystem.sale.configureSalesRoutes
import com.example.routes.inventorysystem.staff.configureStaffRoutes
import com.example.routes.inventorysystem.user.configureUserRoutes
import com.example.security.configureSecurity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
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
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        anyHost()
    }
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
    configureStaffRoutes(RepositoryProvider.provideStaffRepository())
    configureStoryRoutes(RepositoryProvider.provideStoryRepository())
    configureBlogUserRoutes(RepositoryProvider.provideBlogUserRepository())

    routing {
        get("/") {
            call.respondText("Testing this")
        }
    }
}
