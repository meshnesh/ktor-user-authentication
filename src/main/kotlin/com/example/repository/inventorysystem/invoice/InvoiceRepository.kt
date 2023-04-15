package com.example.repository.inventorysystem.invoice

import com.example.base.BaseResponse
import com.example.models.inventorysystem.invoices.InvoicePayload

interface InvoiceRepository {
    suspend fun getAllInvoices(page: Int, limit: Int): BaseResponse<Any>
    suspend fun createInvoice(invoicePayload: InvoicePayload): BaseResponse<Any>
    suspend fun updateInvoice(invoiceId: Int, invoicePayload: InvoicePayload): BaseResponse<Any>
    suspend fun deleteInvoice(invoiceId: Int): BaseResponse<Any>
}