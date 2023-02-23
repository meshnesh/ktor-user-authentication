package com.example.models.invoices

data class InvoicePayload(
    val invoiceId: Int,
    val invoiceName: String,
    val invoiceNumber: String,
    val invoiceDescription: String,
    val invoicePrice: Int,
    val invoiceQuantity: Int,
    val invoiceDateTime: String,
    val invoiceDueDateTime: String,
    val invoiceStatus: String
)
