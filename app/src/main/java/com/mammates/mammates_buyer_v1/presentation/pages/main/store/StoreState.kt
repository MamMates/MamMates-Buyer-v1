package com.mammates.mammates_buyer_v1.presentation.pages.main.store

import com.mammates.mammates_buyer_v1.domain.model.FoodSearch

data class StoreState(
    val token: String = "",
    val isLoading: Boolean = false,
    val isNotAuthorizeDialogOpen: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val isRefresh: Boolean = false,
    val isConfirmBuyDialogOpen: Boolean = false,
    val id: Int = -69,

    val quantity: Map<Int, Int> = mutableMapOf(),
    val total: Int = 0,
    val foods: List<FoodSearch> = emptyList(),
    val storeName: String = "No Data Store Provides",
    val storeAddress: String = "No Data Address Provides",
    val isConfirmDialogOpen: Boolean = false,


    )
