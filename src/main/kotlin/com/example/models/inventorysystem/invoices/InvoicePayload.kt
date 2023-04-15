package com.example.models.inventorysystem.invoices

data class InvoicePayload(
    val companyId: Int,
    val staffId: Int,
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
