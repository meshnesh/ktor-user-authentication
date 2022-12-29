package com.example.security

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.server.application.*

fun Application.configureSecurity(){
//    JwtConfig.initialize("ktor-test")
//    install(Authentication){
//        jwt {
//            verifier(JwtConfig.instance.verifier)
//            validate {
//                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
//                if(claim != null){
//                    UserIdPrincipalForUser(claim)
//                }else {
//                    null
//                }
//            }
//        }
//    }
}
