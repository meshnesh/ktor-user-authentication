package com.example.repository.products

import com.example.base.BaseResponse
import com.example.models.product.AvailableProducts
import com.example.service.products.AvailableProductsService
import com.example.utils.GENERIC_ERROR
import com.example.utils.PRODUCT_DELETE_SUCCESS
import com.example.utils.PRODUCT_UPDATE_SUCCESS
import com.example.utils.SUCCESS

class AvailableProductsRepositoryImpl(
    private val availableProductsService: AvailableProductsService) : AvailableProductsRepository {
    override suspend fun getAllActiveProducts(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = availableProductsService.getAvailableProducts(page, limit), message = SUCCESS)
    }

    override suspend fun addProducts(availableProducts: AvailableProducts): BaseResponse<Any> {
        val availableProduct = availableProductsService.add(availableProducts)
        return if (availableProduct != null) {
            BaseResponse.SuccessResponse(data = availableProduct, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        }
    }

    override suspend fun update(productId: Int, availableProducts: AvailableProducts): BaseResponse<Any> {
        if (availableProductsService.update(productId, availableProducts)) {
            return BaseResponse.SuccessResponse(data = availableProducts, message = PRODUCT_UPDATE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun delete(productId: Int): BaseResponse<Any> {
        if (availableProductsService.delete(productId)) {
            return BaseResponse.SuccessResponse(data = null, message = PRODUCT_DELETE_SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }
}
