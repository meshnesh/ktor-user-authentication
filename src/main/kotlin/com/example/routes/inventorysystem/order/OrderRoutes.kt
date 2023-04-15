package com.example.routes.inventorysystem.order

import com.example.models.inventorysystem.orders.OrdersPayload
import com.example.repository.inventorysystem.orders.OrdersRepository
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureOrderRoutes(repository: OrdersRepository) {
    routing {
        authenticate {
            route("inventory-system/order") {
                get("all-orders") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllOrders(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-order") {
                    val params = call.receive<OrdersPayload>()
                    val result = repository.createOrder(params)
                    call.respond(result.statusCode, result)
                }

                put("update-order/{orderId}") {
                    val id = call.parameters["orderId"]?.toIntOrNull() ?: -1
                    val params = call.receive<OrdersPayload>()
                    val result = repository.updateOrder(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-order/{orderId}") {
                    val id = call.parameters["orderId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteOrder(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}