package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

sealed class OrderDetailEvent {
    data object OnRefreshPage : OrderDetailEvent()
    data object OnDismissDialog : OrderDetailEvent()
    data object ClearToken : OrderDetailEvent()
}