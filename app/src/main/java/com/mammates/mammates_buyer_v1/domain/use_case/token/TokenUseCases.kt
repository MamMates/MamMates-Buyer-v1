package com.mammates.mammates_buyer_v1.domain.use_case.token

data class TokenUseCases(
    val getTokenUseCase: GetTokenUseCase,
    val clearTokenUseCase: ClearTokenUseCase,
    val setTokenUseCase: SetTokenUseCase
)
