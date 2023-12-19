package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderEvent

sealed class OrderDetailEvent{
    data object OnRefreshPage : OrderDetailEvent()
    data object OnDismissDialog : OrderDetailEvent()
    data object ClearToken : OrderDetailEvent()
}