package com.mammates.mammates_buyer_v1.presentation.pages.main.order

sealed class OrderEvent {
    data object OnRefreshPage : OrderEvent()
    data object OnDismissDialog : OrderEvent()
    data object ClearToken : OrderEvent()

}