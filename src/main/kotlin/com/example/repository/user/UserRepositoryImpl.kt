package com.example.repository.user

import com.example.models.CreateUserParams
import com.example.models.UserLoginParams
import com.example.security.JwtConfig
import com.example.service.UserService
import com.example.utils.*

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): String {
        return if (isEmailExist(params.email)) {
            MESSAGE_EMAIL_ALREADY_REGISTERED
//            BaseResponse.ErrorResponse(message = "Email Already Exists")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                USER_REGISTRATION_SUCCESS
//                BaseResponse.SuccessResponse(data = user, message = "User successfully registered")
            } else {
                GENERIC_ERROR
//                BaseResponse.ErrorResponse(message = "SomeThing Went Wrong")
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): String {
        val user = userService.loginUser(params.email, params.password)
        return if (user != null) {
//            val token = JwtConfig.instance.createAccessToken(user.id)
//            user.authToken = token
            USER_LOGIN_SUCCESS
//            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
        } else {
            USER_LOGIN_FAILURE
//            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}