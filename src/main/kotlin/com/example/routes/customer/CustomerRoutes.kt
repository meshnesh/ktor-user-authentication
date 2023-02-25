package com.example.routes.customer

import com.example.models.customers.CustomerPayload
import com.example.repository.customer.CustomerRepository
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureCustomerRoutes(repository: CustomerRepository) {
    routing {
        authenticate {
            route("customer") {
                get("all-customers") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllCustomers(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-customer") {
                    val params = call.receive<CustomerPayload>()
                    val result = repository.createCustomer(params)
                    call.respond(result.statusCode, result)
                }

                put("update-customer/{customerId}") {
                    val id = call.parameters["customerId"]?.toIntOrNull() ?: -1
                    val params = call.receive<CustomerPayload>()
                    val result = repository.updateCustomer(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-customer/{customerId}") {
                    val id = call.parameters["customerId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteCustomer(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}