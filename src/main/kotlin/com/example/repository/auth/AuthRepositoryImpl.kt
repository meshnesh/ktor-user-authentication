package com.example.repository.auth

import com.example.base.BaseResponse
import com.example.models.auth.CreateUserParams
import com.example.models.auth.UserLoginParams
import com.example.security.JwtConfig
import com.example.service.auth.AuthService
import com.example.utils.*

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = authService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = USER_REGISTRATION_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        val user = authService.loginUser(params.email, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE)
        }
    }

    override suspend fun createUser(userRole: String, params: CreateUserParams): BaseResponse<Any> {

        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            if (userRole.equals("Admin")) {}
            val user = authService.registerUser(params)
            if (user != null) {
                // check the role from the JWT session, if it is a developer or admin
                BaseResponse.SuccessResponse(data = user, message = USER_CREATION_SUCCESS)
            } else {
                // else
                BaseResponse.ErrorResponse(GENERIC_ERROR)
            }
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return authService.findUserByEmail(email) != null
    }
}
