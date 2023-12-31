package com.mammates.mammates_buyer_v1.presentation.pages.main.search

sealed class SearchEvent {
    data class OnSearchItem(val keywords: String) : SearchEvent()
    data object OnDismissDialog : SearchEvent()
    data object ClearToken : SearchEvent()
}