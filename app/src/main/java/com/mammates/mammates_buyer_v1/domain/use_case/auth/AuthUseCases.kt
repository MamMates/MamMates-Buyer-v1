package com.mammates.mammates_buyer_v1.domain.use_case.auth

data class AuthUseCases(
    val authLoginUseCase: AuthLoginUseCase,
    val authRegisterUseCase: AuthRegisterUseCase,
)
