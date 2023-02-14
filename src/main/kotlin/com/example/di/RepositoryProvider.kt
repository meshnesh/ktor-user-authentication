package com.example.di

import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.repository.branches.CompanyBranchRepository
import com.example.repository.branches.CompanyBranchRepositoryImpl
import com.example.repository.products.AvailableProductsRepository
import com.example.repository.products.AvailableProductsRepositoryImpl
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.service.auth.AuthServiceImpl
import com.example.service.companyBranch.CompanyBranchServiceImpl
import com.example.service.products.AvailableProductsService
import com.example.service.products.AvailableProductsServiceImpl
import com.example.service.user.UserServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
    fun provideAvailableProductsRepository(): AvailableProductsRepository = AvailableProductsRepositoryImpl(AvailableProductsServiceImpl())
    fun provideCompanyBranchRepository(): CompanyBranchRepository = CompanyBranchRepositoryImpl(CompanyBranchServiceImpl())
}
