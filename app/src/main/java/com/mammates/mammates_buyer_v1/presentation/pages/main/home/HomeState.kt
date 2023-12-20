package com.mammates.mammates_buyer_v1.presentation.pages.main.home

import com.mammates.mammates_buyer_v1.domain.model.FoodRecommendation

data class HomeState(
    val isOnBoarding: Boolean = false,
    val token: String = "",
    val isLoading: Boolean = false,
    val searchText: String = "",
    val isActiveSearch: Boolean = false,
    val isRefresh: Boolean = false,
    val errorMessage: String? = null,
    val isNotAuthorizeDialogOpen: Boolean = false,
    val foodsRecommendation: List<FoodRecommendation> = emptyList()
)
