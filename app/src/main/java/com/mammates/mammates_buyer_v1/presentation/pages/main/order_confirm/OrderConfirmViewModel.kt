package com.mammates.mammates_buyer_v1.presentation.pages.main.order_confirm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OrderConfirmViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(OrderConfirmState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {

    }

}