package com.example.db.extensions

import com.example.db.schemas.AvailableProductsTable
import com.example.db.schemas.BranchesTable
import com.example.db.schemas.UserTable
import com.example.models.companyBranch.CompanyBranch
import com.example.models.product.AvailableProducts
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

fun ResultRow?.toAvailableProducts(): AvailableProducts? {
    return if (this == null) null
    else AvailableProducts(
        productId = this[AvailableProductsTable.productId],
        productName = this[AvailableProductsTable.productName],
        productDescription = this[AvailableProductsTable.productDescription],
        productPrice = this[AvailableProductsTable.productPrice],
        productQuantity = this[AvailableProductsTable.productQuantity]
    )
}

fun ResultRow?.toCompanyBranch(): CompanyBranch? {
    return if (this==null) null
    else CompanyBranch (
        id = this[BranchesTable.id],
        branchName = this[BranchesTable.branchName],
        branchLocation = this[BranchesTable.branchLocation],
        userId = this[BranchesTable.userId]
    )
}
