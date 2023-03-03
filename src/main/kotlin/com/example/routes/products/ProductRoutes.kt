package com.example.routes.products

import com.example.models.product.AvailableProductsPayload
import com.example.repository.products.ProductsRepository
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureProductRoutes(repository: ProductsRepository) {
    routing {
        authenticate {
            route("products") {

                get("all-products") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllActiveProducts(page, limit)
                    call.respond(result.statusCode, result)
                }

                get("{companyId}/products") {
                    val companyId = call.parameters["companyId"]?.toIntOrNull() ?: -1
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getCompanyProducts(companyId, page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-products") {
                    val params = call.receive<AvailableProductsPayload>()
                    val result = repository.addProducts(params)
                    call.respond(result.statusCode, result)
                }

                put("update-product/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1
                    val params = call.receive<AvailableProductsPayload>()
                    val result = repository.update(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-product/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1
                    val result = repository.delete(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}