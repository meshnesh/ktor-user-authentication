package com.example.repository.customer

import com.example.base.BaseResponse
import com.example.models.customers.CustomerPayload
import com.example.service.customer.CustomerService
import com.example.utils.*

class CustomerRepositoryImpl(private val customerService: CustomerService) : CustomerRepository {
    override suspend fun getAllCustomers(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = customerService.getAllCustomers(page, limit), message = SUCCESS)
    }

    override suspend fun createCustomer(customerPayload: CustomerPayload): BaseResponse<Any> {
        val customer = customerService.createCustomer(customerPayload)
        return if (customer != null) BaseResponse.SuccessResponse(
            data = customer,
            message = CUSTOMER_CREATION_SUCCESS
        ) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateCustomer(customerId: Int, customerPayload: CustomerPayload): BaseResponse<Any> {
        return if (customerService.updateCustomer(customerId, customerPayload)) {
            BaseResponse.SuccessResponse(data = customerPayload, message = CUSTOMER_UPDATE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteCustomer(customerId: Int): BaseResponse<Any> {
        return if (customerService.deleteCustomer(customerId)) {
            BaseResponse.SuccessResponse(data = null, message = COMPANY_DELETE_SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}
