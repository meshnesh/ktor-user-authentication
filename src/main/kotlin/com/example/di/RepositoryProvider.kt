package com.example.di

import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.repository.branches.CompanyBranchRepository
import com.example.repository.branches.CompanyBranchRepositoryImpl
import com.example.repository.company.CompanyRepository
import com.example.repository.company.CompanyRepositoryImpl
import com.example.repository.customer.CustomerRepository
import com.example.repository.customer.CustomerRepositoryImpl
import com.example.repository.invoice.InvoiceRepository
import com.example.repository.invoice.InvoiceRepositoryImpl
import com.example.repository.orders.OrdersRepository
import com.example.repository.orders.OrdersRepositoryImpl
import com.example.repository.products.ProductsRepository
import com.example.repository.products.ProductsRepositoryImpl
import com.example.repository.sales.SalesRepository
import com.example.repository.sales.SalesRepositoryImpl
import com.example.repository.staff.StaffRepository
import com.example.repository.staff.StaffRepositoryImpl
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.service.auth.AuthServiceImpl
import com.example.service.company.CompanyServiceImpl
import com.example.service.company.companyBranch.CompanyBranchServiceImpl
import com.example.service.customer.CustomerServiceImpl
import com.example.service.invoice.InvoiceServiceImpl
import com.example.service.orders.OrdersServiceImpl
import com.example.service.products.ProductsServiceImpl
import com.example.service.sales.SalesServiceImpl
import com.example.service.staff.StaffServiceImpl
import com.example.service.user.UserServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
    fun provideProductsRepository(): ProductsRepository = ProductsRepositoryImpl(ProductsServiceImpl())
    fun provideCompanyRepository(): CompanyRepository = CompanyRepositoryImpl(CompanyServiceImpl())
    fun provideCompanyBranchRepository(): CompanyBranchRepository = CompanyBranchRepositoryImpl(CompanyBranchServiceImpl())
    fun provideCustomerRepository(): CustomerRepository = CustomerRepositoryImpl(CustomerServiceImpl())
    fun provideInvoiceRepository(): InvoiceRepository = InvoiceRepositoryImpl(InvoiceServiceImpl())
    fun provideOrdersRepository(): OrdersRepository = OrdersRepositoryImpl(OrdersServiceImpl())
    fun provideSalesRepository(): SalesRepository = SalesRepositoryImpl(SalesServiceImpl())
    fun provideStaffRepository(): StaffRepository = StaffRepositoryImpl(StaffServiceImpl())
}
