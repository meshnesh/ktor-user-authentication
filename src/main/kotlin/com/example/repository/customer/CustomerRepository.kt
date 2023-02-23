package com.example.repository.customer

import com.example.base.BaseResponse
import com.example.models.customers.CustomerPayload

interface CustomerRepository {
    suspend fun getAllCustomers(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createCustomer(customerPayload: CustomerPayload): BaseResponse<Any>
    suspend fun updateCustomer(customerId: Int, customerPayload: CustomerPayload): BaseResponse<Any>
    suspend fun deleteCustomer(customerId: Int): BaseResponse<Any>
}