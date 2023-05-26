package com.example.db.extensions

import com.example.db.schemas.blogs.comment.CommentTable
import com.example.db.schemas.blogs.story.StoryTable
import com.example.db.schemas.blogs.users.BlogUserTable
import com.example.db.schemas.invetorysystem.companySchema.CompanyBranchTable
import com.example.db.schemas.invetorysystem.companySchema.CompanyDbTable
import com.example.db.schemas.invetorysystem.customers.CustomerTable
import com.example.db.schemas.invetorysystem.invoice.InvoiceTable
import com.example.db.schemas.invetorysystem.orders.OrdersTable
import com.example.db.schemas.invetorysystem.productsSchema.ProductsTable
import com.example.db.schemas.invetorysystem.sales.SalesTable
import com.example.db.schemas.invetorysystem.staff.StaffTable
import com.example.db.schemas.invetorysystem.users.InvetoryUserTable
import com.example.models.blogs.comment.Comment
import com.example.models.blogs.story.AddStory
import com.example.models.blogs.story.Story
import com.example.models.blogs.users.BlogUser
import com.example.models.inventorysystem.company.CompanyPayload
import com.example.models.inventorysystem.company.companyBranch.CompanyBranchPayload
import com.example.models.inventorysystem.customers.CustomerPayload
import com.example.models.inventorysystem.invoices.InvoicePayload
import com.example.models.inventorysystem.orders.OrdersPayload
import com.example.models.inventorysystem.product.AvailableProductsPayload
import com.example.models.inventorysystem.sales.SalesPayload
import com.example.models.inventorysystem.staff.CompanyStaff
import com.example.models.inventorysystem.user.InventoryUser
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): InventoryUser? {
    return if (this == null) null
    else InventoryUser(
        id = this[InvetoryUserTable.id],
        fullName = this[InvetoryUserTable.fullName],
        avatar = this[InvetoryUserTable.avatar],
        email = this[InvetoryUserTable.email],
        createdAt = this[InvetoryUserTable.createdAt].toString(),
        idNo = this[InvetoryUserTable.idNo],
        isAdmin = this[InvetoryUserTable.isAdmin],
        role = this[InvetoryUserTable.role],
        joinDate = this[InvetoryUserTable.joinDate]
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
    return if (this == null) null
    else CompanyPayload(
        companyId = this[CompanyDbTable.companyId],
        userId = this[CompanyDbTable.userId],
        companyName = this[CompanyDbTable.companyName],
        companySubscription = this[CompanyDbTable.companySubscription],
        companySubscriptionStatus = this[CompanyDbTable.companySubscriptionStatus]
    )
}

fun ResultRow?.toCompanyBranch(): CompanyBranchPayload? {
    return if (this == null) null
    else CompanyBranchPayload(
        companyId = this[CompanyBranchTable.companyId],
        branchId = this[CompanyBranchTable.branchId],
        branchName = this[CompanyBranchTable.branchName],
        branchLocation = this[CompanyBranchTable.branchLocation]
    )
}

fun ResultRow?.toOrders(): OrdersPayload? {
    return if (this == null) null
    else OrdersPayload(
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
    return if (this == null) null
    else InvoicePayload(
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
    return if (this == null) null
    else SalesPayload(
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
    return if (this == null) null
    else CustomerPayload(
        companyId = this[CustomerTable.companyId],
        customerId = this[CustomerTable.customerId],
        customerName = this[CustomerTable.customerName],
        customerPhoneNumber = this[CustomerTable.customerPhoneNumber],
        customerAddress = this[CustomerTable.customerAddress]
    )
}

//Blogs section
fun ResultRow?.toAddStory(): AddStory? {
    return if (this == null) null
    else AddStory (
        id = this[StoryTable.id],
        userId = this[StoryTable.userId],
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        shortDescription = this[StoryTable.shortDescription],
        category = this[StoryTable.category],
        coverImgUrl = this[StoryTable.coverImgUrl],
        isDraft = this[StoryTable.isDraft],
        isUpdated = this[StoryTable.isUpdated],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toStory(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        shortDescription = this[StoryTable.shortDescription],
        category = this[StoryTable.category],
        coverImgUrl = this[StoryTable.coverImgUrl],
        isDraft = this[StoryTable.isDraft],
        isUpdated = this[StoryTable.isUpdated],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toStoryJoinedWithUser(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        blogUser = BlogUser(
            id = this[BlogUserTable.id],
            fullName = this[BlogUserTable.fullName],
            avatar = this[BlogUserTable.avatar],
            email = this[BlogUserTable.email],
            createdAt = this[BlogUserTable.createdAt].toString()
        ),
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        shortDescription = this[StoryTable.shortDescription],
        category = this[StoryTable.category],
        coverImgUrl = this[StoryTable.coverImgUrl],
        isDraft = this[StoryTable.isDraft],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toComment(): Comment? {
    return if (this == null) null
    else Comment(
        id = this[CommentTable.id],
        userId = this[CommentTable.userId],
        storyId = this[CommentTable.storyId],
        comment = this[CommentTable.comment],
        createdAt = this[CommentTable.createdAt].toString()
    )
}

fun ResultRow?.toBlogUser(): BlogUser? {
    return if (this == null) null
    else BlogUser(
        id = this[BlogUserTable.id],
        fullName = this[BlogUserTable.fullName],
        avatar = this[BlogUserTable.avatar],
        email = this[BlogUserTable.email],
        createdAt = this[BlogUserTable.createdAt].toString()
    )
}
