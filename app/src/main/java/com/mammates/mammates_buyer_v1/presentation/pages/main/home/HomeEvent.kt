package com.mammates.mammates_buyer_v1.presentation.pages.main.home

import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderEvent

sealed class HomeEvent {
    data class OnChangeSearchText(val text: String) : HomeEvent()
    data class OnActiveSearch(val isActive: Boolean) : HomeEvent()
    data object OnRefreshPage : HomeEvent()
    data object ClearToken : HomeEvent()

}