package com.example.repository.user

import com.example.base.BaseResponse
import com.example.service.user.UserService

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUser(id))
    }
}
