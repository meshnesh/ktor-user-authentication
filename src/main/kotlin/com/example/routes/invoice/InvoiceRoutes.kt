package com.example.routes.invoice

import com.example.models.invoices.InvoicePayload
import com.example.repository.invoice.InvoiceRepository
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureInvoiceRoutes(repository: InvoiceRepository) {
    routing {
        authenticate {
            route("invoice") {
                get("all-invoices") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllInvoices(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-invoice") {
                    val params = call.receive<InvoicePayload>()
                    val result = repository.createInvoice(params)
                    call.respond(result.statusCode, result)
                }

                put("update-invoice/{invoiceId}") {
                    val id = call.parameters["invoiceId"]?.toIntOrNull() ?: -1
                    val params = call.receive<InvoicePayload>()
                    val result = repository.updateInvoice(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-invoice/{invoiceId}") {
                    val id = call.parameters["invoiceId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteInvoice(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}