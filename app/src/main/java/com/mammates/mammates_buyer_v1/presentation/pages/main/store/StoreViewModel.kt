package com.mammates.mammates_buyer_v1.presentation.pages.main.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.domain.use_case.food.FoodUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.order.OrderUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import com.mammates.mammates_buyer_v1.util.HttpError
import com.mammates.mammates_buyer_v1.util.toListOrderBuyerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val foodUseCases: FoodUseCases,
    private val orderUseCases: OrderUseCases,
    private val tokenUseCases: TokenUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(StoreState())
    val state = _state.asStateFlow()

    init {
        getTokenValue()

        savedStateHandle.get<Int>("store_id")?.let { id ->
            _state.value = _state.value.copy(
                id = id
            )
            if (id != -69) {
                getStoreDataFromFood()
            }
        }
    }

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
                    },
                )
                getTotal()
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
                getTotal()
            }

            StoreEvent.OnSubmitOrder -> {
                postOrder()
                Log.i("StoreViewModel", _state.value.quantity.toListOrderBuyerItem().toString())
            }

            StoreEvent.ClearToken -> {
                viewModelScope.launch {
                    tokenUseCases.clearTokenUseCase()
                }
                _state.value = _state.value.copy(
                    token = ""
                )
            }

            StoreEvent.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null
                )
            }

            StoreEvent.OnDismissDialogConfirm -> {
                _state.value = _state.value.copy(
                    isConfirmDialogOpen = false
                )
            }

            StoreEvent.OnOpenDialogConfirm -> {
                _state.value = _state.value.copy(
                    isConfirmDialogOpen = true
                )
            }
        }
    }

    private fun getTokenValue() {
        _state.value = _state.value.copy(
            token = tokenUseCases.getTokenUseCase()
        )
    }

    private fun postOrder() {
        orderUseCases.postOrderUseCase(
            token = _state.value.token,
            food = _state.value.quantity.toListOrderBuyerItem(),
            sellerId = _state.value.id
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    if (result.message.equals(HttpError.UNAUTHORIZED.message)) {
                        _state.value = _state.value.copy(
                            isNotAuthorizeDialogOpen = true,
                            isLoading = false,
                        )
                        return@onEach
                    }

                    _state.value = _state.value.copy(
                        errorMessage = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        successMessage = result.data,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStoreDataFromFood() {
        foodUseCases.getStoreFoodUseCase(_state.value.token, _state.value.id)
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        if (result.message.equals(HttpError.UNAUTHORIZED.message)) {
                            _state.value = _state.value.copy(
                                isNotAuthorizeDialogOpen = true,
                                isLoading = false,
                            )
                            return@onEach
                        }

                        _state.value = _state.value.copy(
                            errorMessage = result.message,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { data ->
                            _state.value = _state.value.copy(
                                foods = data.foods,
                                storeName = data.seller,
                                storeAddress = data.seller,
                                isLoading = false
                            )
                        }
                        _state.value = _state.value.copy(
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getPriceFromQuantity(quantity: Int, price: Int): Int {
        return quantity * price
    }

    private fun getTotal() {
        var total = 0
        _state.value.quantity.mapValues { mapItem ->
            total += getPriceFromQuantity(
                mapItem.value,
                _state.value.foods.find { it.id == mapItem.key }?.price ?: 0
            )
        }
        _state.value = _state.value.copy(
            total = total
        )
    }


}