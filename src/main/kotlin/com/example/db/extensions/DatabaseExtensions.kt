package com.example.db.extensions

import com.example.db.schemas.UserTable
import com.example.db.schemas.companySchema.CompanyBranchTable
import com.example.db.schemas.companySchema.CompanyDbTable
import com.example.db.schemas.customers.CustomerTable
import com.example.db.schemas.invoice.InvoiceTable
import com.example.db.schemas.orders.OrdersTable
import com.example.db.schemas.productsSchema.ProductsTable
import com.example.db.schemas.sales.SalesTable
import com.example.db.schemas.staff.StaffTable
import com.example.models.company.CompanyPayload
import com.example.models.company.companyBranch.CompanyBranchPayload
import com.example.models.customers.CustomerPayload
import com.example.models.invoices.InvoicePayload
import com.example.models.orders.OrdersPayload
import com.example.models.product.AvailableProductsPayload
import com.example.models.sales.SalesPayload
import com.example.models.staff.CompanyStaff
import com.example.models.user.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        avatar = this[UserTable.avatar],
        email = this[UserTable.email],
        createdAt = this[UserTable.createdAt].toString(),
        idNo = this[UserTable.idNo],
        isAdmin = this[UserTable.isAdmin],
        role = this[UserTable.role],
        joinDate = this[UserTable.joinDate]
    )
}

fun ResultRow?.toStaff(): CompanyStaff? {
    return if (this == null) null
    else CompanyStaff(
        staffId = this[StaffTable.staffId],
        staffFirstName = this[StaffTable.staffFirstName],
        staffLastName = this[StaffTable.staffLastName],
        companyId = this[StaffTable.companyId],
        staffAvatar = this[StaffTable.staffAvatar],
        staffEmail = this[StaffTable.staffEmail],
        staffCreatedAt = this[StaffTable.staffCreatedAt].toString(),
        staffIdNo = this[StaffTable.staffIdNo],
        staffIsAdmin = this[StaffTable.staffIsAdmin],
        staffRole = this[StaffTable.staffRole],
        staffJoinDate = this[StaffTable.staffJoinDate],
        staffPassword = this[StaffTable.staffPassword],
        userId = this[StaffTable.userId]
    )
}

fun ResultRow?.toAvailableProducts(): AvailableProductsPayload? {
    return if (this == null) null
    else AvailableProductsPayload(
        companyId = this[ProductsTable.companyId],
        productId = this[ProductsTable.productId],
        productName = this[ProductsTable.productName],
        productDescription = this[ProductsTable.productDescription],
        productPrice = this[ProductsTable.productPrice],
        productQuantity = this[ProductsTable.productQuantity],
        productCode = this[ProductsTable.productCode],
        productImage = this[ProductsTable.productImage]
    )
}

fun ResultRow?.toCompany(): CompanyPayload? {
    return if (this==null) null
    else CompanyPayload (
        companyId = this[CompanyDbTable.companyId],
        userId = this[CompanyDbTable.userId],
        companyName = this[CompanyDbTable.companyName],
        companySubscription = this[CompanyDbTable.companySubscription],
        companySubscriptionStatus = this[CompanyDbTable.companySubscriptionStatus]
    )
}

fun ResultRow?.toCompanyBranch(): CompanyBranchPayload? {
    return if (this==null) null
    else CompanyBranchPayload (
        companyId = this[CompanyBranchTable.companyId],
        branchId = this[CompanyBranchTable.branchId],
        branchName = this[CompanyBranchTable.branchName],
        branchLocation = this[CompanyBranchTable.branchLocation]
    )
}

fun ResultRow?.toOrders(): OrdersPayload? {
    return if (this==null) null
    else OrdersPayload (
        companyId = this[OrdersTable.companyId],
        orderId = this[OrdersTable.orderId],
        staffId = this[OrdersTable.staffId],
        orderName = this[OrdersTable.orderName],
        orderDateTime = this[OrdersTable.orderDateTime],
        orderTotalPrice = this[OrdersTable.orderTotalPrice],
        orderStatus = this[OrdersTable.orderStatus]
    )
}

fun ResultRow?.toInvoice(): InvoicePayload? {
    return if (this==null) null
    else InvoicePayload (
        companyId = this[InvoiceTable.companyId],
        staffId = this[InvoiceTable.staffId],
        invoiceId = this[InvoiceTable.invoiceId],
        invoiceName = this[InvoiceTable.invoiceName],
        invoiceNumber = this[InvoiceTable.invoiceNumber],
        invoiceDescription = this[InvoiceTable.invoiceDescription],
        invoicePrice = this[InvoiceTable.invoicePrice],
        invoiceQuantity = this[InvoiceTable.invoiceQuantity],
        invoiceDateTime = this[InvoiceTable.invoiceDateTime],
        invoiceDueDateTime = this[InvoiceTable.invoiceDueDateTime],
        invoiceStatus = this[InvoiceTable.invoiceStatus],
    )
}

fun ResultRow?.toSales(): SalesPayload? {
    return if (this==null) null
    else SalesPayload (
        companyId = this[SalesTable.companyId],
        staffId = this[SalesTable.staffId],
        orderId = this[SalesTable.orderId],
        saleId = this[SalesTable.saleId],
        saleName = this[SalesTable.saleName],
        saleDateTime = this[SalesTable.saleDateTime],
        saleTotalPrice = this[SalesTable.saleTotalPrice],
        saleStatus = this[SalesTable.saleStatus]
    )
}

fun ResultRow?.toCustomer(): CustomerPayload? {
    return if (this==null) null
    else CustomerPayload (
        companyId = this[CustomerTable.companyId],
        customerId = this[CustomerTable.customerId],
        customerName = this[CustomerTable.customerName],
        customerPhoneNumber = this[CustomerTable.customerPhoneNumber],
        customerAddress = this[CustomerTable.customerAddress]
    )
}
