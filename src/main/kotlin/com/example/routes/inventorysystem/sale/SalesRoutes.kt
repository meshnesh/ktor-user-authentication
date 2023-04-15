package com.example.routes.inventorysystem.sale

import com.example.models.inventorysystem.sales.SalesPayload
import com.example.repository.inventorysystem.sales.SalesRepository
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSalesRoutes(repository: SalesRepository) {
    routing {
        authenticate {
            route("inventory-system/sale") {
                get("all-sales") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllSales(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-sale") {
                    val params = call.receive<SalesPayload>()
                    val result = repository.createSale(params)
                    call.respond(result.statusCode, result)
                }

                put("update-sale/{saleId}") {
                    val id = call.parameters["saleId"]?.toIntOrNull() ?: -1
                    val params = call.receive<SalesPayload>()
                    val result = repository.updateSale(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-sale/{saleId}") {
                    val id = call.parameters["saleId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteSale(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}