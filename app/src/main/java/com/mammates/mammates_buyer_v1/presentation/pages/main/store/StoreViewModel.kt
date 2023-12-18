package com.mammates.mammates_buyer_v1.presentation.pages.main.store

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mammates.mammates_buyer_v1.util.toListOrderBuyerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(StoreState())
    val state = _state.asStateFlow()

    fun onEvent(event: StoreEvent) {
        when (event) {
            is StoreEvent.PutMapQuantity -> {
                _state.value = _state.value.copy(
                    quantity = _state.value.quantity.plus(event.key to 0)
                )
            }

            is StoreEvent.OnAddQuantity -> {
                _state.value = _state.value.copy(
                    quantity = _state.value.quantity.mapValues {
                        if (it.key == event.key) {
                            it.value.plus(1)
                        } else {
                            it.value
                        }
                    }
                )
            }

            is StoreEvent.OnRemoveQuantity -> {
                _state.value = _state.value.copy(
                    quantity = _state.value.quantity.mapValues {
                        if (it.key == event.key) {
                            it.value.minus(1)
                        } else {
                            it.value
                        }
                    }
                )
            }

            StoreEvent.OnSubmitOrder -> {
                Log.i("StoreViewModel", _state.value.quantity.toListOrderBuyerItem().toString())
            }
        }
    }

}