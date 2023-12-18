package com.mammates.mammates_buyer_v1.presentation.pages.main.store

sealed class StoreEvent{
    data class PutMapQuantity(val key: Int) : StoreEvent()
    data class OnAddQuantity(val key: Int) : StoreEvent()
    data class OnRemoveQuantity(val key: Int) : StoreEvent()
    data object OnSubmitOrder : StoreEvent()
}