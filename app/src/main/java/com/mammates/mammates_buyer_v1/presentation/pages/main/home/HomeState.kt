package com.mammates.mammates_buyer_v1.presentation.pages.main.home

data class HomeState(
    val isOnBoarding: Boolean = false,
    val token: String = "",
    val isLoading: Boolean = false,
    val searchText: String = "",
    val isActiveSearch: Boolean = false,
)
