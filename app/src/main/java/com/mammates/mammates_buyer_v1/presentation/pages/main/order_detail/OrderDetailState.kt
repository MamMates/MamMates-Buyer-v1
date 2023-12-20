package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

import com.mammates.mammates_buyer_v1.domain.model.FoodOrder
import com.mammates.mammates_buyer_v1.util.StatusOrder

data class OrderDetailState(
    val id: Int = -69,
    val token: String = "",
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val errorMessage: String? = null,
    val isNotAuthorizeDialogOpen: Boolean = false,

    val invoice: String = "",
    val storeName: String = "No Data Store Provides",
    val date: String = "",
    val foods: List<FoodOrder> = emptyList(),
    val total: Int = 0,
    val status: StatusOrder = StatusOrder.Unidentified


)
