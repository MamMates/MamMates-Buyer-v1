package com.mammates.mammates_buyer_v1.presentation.pages.main.home

sealed class HomeEvent {
    data class OnChangeSearchText(val text: String) : HomeEvent()
    data class OnActiveSearch(val isActive: Boolean) : HomeEvent()
}