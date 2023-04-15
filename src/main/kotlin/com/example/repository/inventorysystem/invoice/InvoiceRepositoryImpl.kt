package com.example.repository.inventorysystem.invoice

import com.example.base.BaseResponse
import com.example.models.inventorysystem.invoices.InvoicePayload
import com.example.service.inventorysystem.invoice.InvoiceService
import com.example.utils.GENERIC_ERROR
import com.example.utils.SUCCESS

class InvoiceRepositoryImpl(private val invoiceService: InvoiceService) : InvoiceRepository {
    override suspend fun getAllInvoices(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = invoiceService.getAllInvoices(page, limit), message = SUCCESS)
    }

    override suspend fun createInvoice(invoicePayload: InvoicePayload): BaseResponse<Any> {
        val invoice = invoiceService.createInvoice(invoicePayload)
        return if (invoice != null) BaseResponse.SuccessResponse(
            data = invoice,
            message = SUCCESS
        ) else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun updateInvoice(invoiceId: Int, invoicePayload: InvoicePayload): BaseResponse<Any> {
        return if (invoiceService.updateInvoice(invoiceId, invoicePayload)) {
            BaseResponse.SuccessResponse(data = invoicePayload, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deleteInvoice(invoiceId: Int): BaseResponse<Any> {
        return if (invoiceService.deleteInvoice(invoiceId)) {
            BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        } else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}