package com.mammates.mammates_buyer_v1.presentation.pages.main.order

import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.FoodOrderItemDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderItemDto
import com.mammates.mammates_buyer_v1.domain.model.Order
import com.mammates.mammates_buyer_v1.util.toOrder

data class OrderState(
    val orders: List<Order> = emptyList(),
    val token : String = "",
    val isLoading : Boolean = false,
    val isRefresh : Boolean = false,
    val errorMessage : String? = null,
    val isNotAuthorizeDialogOpen : Boolean = false,
)
