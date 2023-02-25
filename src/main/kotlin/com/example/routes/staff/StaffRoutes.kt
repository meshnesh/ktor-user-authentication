package com.example.routes.staff

import com.example.models.staff.CompanyStaff
import com.example.repository.staff.StaffRepository
import com.example.security.UserIdPrincipalForUser
import com.example.utils.DEFAULT_LIMIT_SIZE
import com.example.utils.DEFAULT_PAGE_START
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureStaffRoutes(repository: StaffRepository) {
    routing {
        authenticate {
            route("staff") {
                get("all-staff") {
                    val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    val result = repository.getAllStaff(page, limit)
                    call.respond(result.statusCode, result)
                }

                post("add-company-staff") {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val params = call.receive<CompanyStaff>()
                    val result = repository.createCompanyStaff(principal?.id!!, params)
                    call.respond(result.statusCode, result)
                }

                put("update-company-staff/{companyId}") {
                    val id = call.parameters["companyId"]?.toIntOrNull() ?: -1
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val params = call.receive<CompanyStaff>()
                    val result = repository.updateCompanyStaff(id, principal?.id!!, params)
                    call.respond(result.statusCode, result)
                }

                delete("delete-company-staff/{companyId}") {
                    val id = call.parameters["companyId"]?.toIntOrNull() ?: -1
                    val result = repository.deleteCompanyStaff(id)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}