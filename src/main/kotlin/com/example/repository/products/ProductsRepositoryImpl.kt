package com.example.repository.products

import com.example.base.BaseResponse
import com.example.models.product.AvailableProductsPayload
import com.example.service.products.ProductsService
import com.example.utils.GENERIC_ERROR
import com.example.utils.PRODUCT_DELETE_SUCCESS
import com.example.utils.PRODUCT_UPDATE_SUCCESS
import com.example.utils.SUCCESS

class ProductsRepositoryImpl(
    private val productsService: ProductsService) : ProductsRepository {
    override suspend fun getAllActiveProducts(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = productsService.getAvailableProducts(page, limit), message = SUCCESS)
    }

    override suspend fun getCompanyProducts(companyId: Int, page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = productsService.getCompanyProducts(companyId, page, limit), message = SUCCESS)
    }

    override suspend fun addProducts(availableProductsPayload: AvailableProductsPayload): BaseResponse<Any> {
        val product = productsService.add(availableProductsPayload)
        return if (product != null) {
            BaseResponse.SuccessResponse(data = product, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        }
    }

    override suspend fun update(productId: Int, availableProductsPayload: AvailableProductsPayload): BaseResponse<Any> {
        if (productsService.update(productId, availableProductsPayload)) {
            return BaseResponse.SuccessResponse(data = availableProductsPayload, message = PRODUCT_UPDATE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun delete(productId: Int): BaseResponse<Any> {
        if (productsService.delete(productId)) {
            return BaseResponse.SuccessResponse(data = null, message = PRODUCT_DELETE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}
