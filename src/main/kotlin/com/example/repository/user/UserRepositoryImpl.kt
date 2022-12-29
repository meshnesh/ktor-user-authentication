package com.example.repository.user

import com.example.models.CreateUserParams
import com.example.models.UserLoginParams
import com.example.service.UserService
import com.example.utils.*

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): SuccessResponse {
        return if (isEmailExist(params.email)) {
            SuccessResponse(params, MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                SuccessResponse(params, USER_REGISTRATION_SUCCESS)
            } else {
                SuccessResponse(params, GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): LoginSuccessResponse {
        val user = userService.loginUser(params.email, params.password)
        return if (user != null) {
            LoginSuccessResponse(params, USER_LOGIN_SUCCESS)
        } else {
            LoginSuccessResponse(params, USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}
