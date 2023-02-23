package com.example.service.customer

import com.example.models.common.PaginatedResult
import com.example.models.customers.CustomerPayload

interface CustomerService {
    suspend fun getAllCustomers(page: Int, limit: Int): PaginatedResult<CustomerPayload>
    suspend fun createCustomer(customerPayload: CustomerPayload): CustomerPayload?
    suspend fun updateCustomer(customerId: Int, customerPayload: CustomerPayload): Boolean
    suspend fun deleteCustomer(customerId: Int): Boolean
}