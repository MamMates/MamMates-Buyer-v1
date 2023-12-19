package com.mammates.mammates_buyer_v1.domain.use_case.order

data class OrderUseCases(
    val getOrderDetailUseCase: GetOrderDetailUseCase,
    val getOrdersUseCase: GetOrdersUseCase,
    val postOrderUseCase: PostOrderUseCase
)
