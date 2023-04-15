package com.example.routes.inventorysystem.company

import com.example.models.inventorysystem.company.CompanyPayload
import com.example.repository.inventorysystem.company.CompanyRepository
import com.example.security.UserIdPrincipalForUser
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureCompanyRoutes(repository: CompanyRepository) {
    routing {
        authenticate {
            route("inventory-system/company") {
                get("all-companies") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllCompanies(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-company") {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val params = call.receive<CompanyPayload>()
                    val result = repository.createCompany(principal?.id!!, params)
                    call.respond(result.statusCode, result)
                }

                put("update-company/{companyId}") {
                    val id = call.parameters["companyId"]?.toIntOrNull() ?: -1
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val params = call.receive<CompanyPayload>()
                    val result = repository.updateCompany(id, principal?.id!!, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-company/{companyId}") {
                    val id = call.parameters["companyId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteCompany(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}