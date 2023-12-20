package com.mammates.mammates_buyer_v1.presentation.pages.main.order

import com.mammates.mammates_buyer_v1.domain.model.Order

data class OrderState(
    val orders: List<Order> = emptyList(),
    val token: String = "",
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val errorMessage: String? = null,
    val isNotAuthorizeDialogOpen: Boolean = false,
)
