package com.example.di

import com.example.repository.blogs.story.StoryRepository
import com.example.repository.blogs.story.StoryRepositoryImpl
import com.example.repository.blogs.user.BlogUserRepository
import com.example.repository.blogs.user.BlogUserRepositoryImpl
import com.example.repository.inventorysystem.auth.AuthRepository
import com.example.repository.inventorysystem.auth.AuthRepositoryImpl
import com.example.repository.inventorysystem.branches.CompanyBranchRepository
import com.example.repository.inventorysystem.branches.CompanyBranchRepositoryImpl
import com.example.repository.inventorysystem.company.CompanyRepository
import com.example.repository.inventorysystem.company.CompanyRepositoryImpl
import com.example.repository.inventorysystem.customer.CustomerRepository
import com.example.repository.inventorysystem.customer.CustomerRepositoryImpl
import com.example.repository.inventorysystem.invoice.InvoiceRepository
import com.example.repository.inventorysystem.invoice.InvoiceRepositoryImpl
import com.example.repository.inventorysystem.orders.OrdersRepository
import com.example.repository.inventorysystem.orders.OrdersRepositoryImpl
import com.example.repository.inventorysystem.products.ProductsRepository
import com.example.repository.inventorysystem.products.ProductsRepositoryImpl
import com.example.repository.inventorysystem.sales.SalesRepository
import com.example.repository.inventorysystem.sales.SalesRepositoryImpl
import com.example.repository.inventorysystem.staff.StaffRepository
import com.example.repository.inventorysystem.staff.StaffRepositoryImpl
import com.example.repository.inventorysystem.user.InventoryUserRepository
import com.example.repository.inventorysystem.user.InventoryUserRepositoryImpl
import com.example.service.blogs.story.StoryServiceImpl
import com.example.service.blogs.user.BlogUserServiceImpl
import com.example.service.inventorysystem.auth.AuthServiceImpl
import com.example.service.inventorysystem.company.CompanyServiceImpl
import com.example.service.inventorysystem.company.companyBranch.CompanyBranchServiceImpl
import com.example.service.inventorysystem.customer.CustomerServiceImpl
import com.example.service.inventorysystem.invoice.InvoiceServiceImpl
import com.example.service.inventorysystem.orders.OrdersServiceImpl
import com.example.service.inventorysystem.products.ProductsServiceImpl
import com.example.service.inventorysystem.sales.SalesServiceImpl
import com.example.service.inventorysystem.staff.StaffServiceImpl
import com.example.service.inventorysystem.user.InventoryUserServiceImpl

object RepositoryProvider {

//    Inventory system repositories
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): InventoryUserRepository = InventoryUserRepositoryImpl(InventoryUserServiceImpl())
    fun provideProductsRepository(): ProductsRepository = ProductsRepositoryImpl(ProductsServiceImpl())
    fun provideCompanyRepository(): CompanyRepository = CompanyRepositoryImpl(CompanyServiceImpl())
    fun provideCompanyBranchRepository(): CompanyBranchRepository = CompanyBranchRepositoryImpl(CompanyBranchServiceImpl())
    fun provideCustomerRepository(): CustomerRepository = CustomerRepositoryImpl(CustomerServiceImpl())
    fun provideInvoiceRepository(): InvoiceRepository = InvoiceRepositoryImpl(InvoiceServiceImpl())
    fun provideOrdersRepository(): OrdersRepository = OrdersRepositoryImpl(OrdersServiceImpl())
    fun provideSalesRepository(): SalesRepository = SalesRepositoryImpl(SalesServiceImpl())
    fun provideStaffRepository(): StaffRepository = StaffRepositoryImpl(StaffServiceImpl())

//    Blog repositories
    fun provideStoryRepository(): StoryRepository = StoryRepositoryImpl(StoryServiceImpl())
    fun provideBlogUserRepository(): BlogUserRepository = BlogUserRepositoryImpl(BlogUserServiceImpl())
}
