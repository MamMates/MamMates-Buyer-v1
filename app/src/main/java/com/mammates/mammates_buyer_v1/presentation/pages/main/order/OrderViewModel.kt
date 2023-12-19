package com.mammates.mammates_buyer_v1.presentation.pages.main.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.domain.use_case.order.OrderUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import com.mammates.mammates_buyer_v1.util.HttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val tokenUseCases: TokenUseCases,
    private val orderUseCases: OrderUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(OrderState())
    val state = _state.asStateFlow()

    init {
        getTokenValue()
    }

    fun onEvent(event: OrderEvent) {
        when (event) {
            OrderEvent.OnRefreshPage -> {
                getTokenValue()
                getOrder()
            }

            OrderEvent.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null
                )
            }

            OrderEvent.ClearToken -> {
                viewModelScope.launch {
                    tokenUseCases.clearTokenUseCase()
                }
                _state.value = _state.value.copy(
                    token = ""
                )
            }
        }

    }

    private fun getTokenValue() {
        _state.value = _state.value.copy(
            token = tokenUseCases.getTokenUseCase()
        )
    }

    private fun getOrder() {
        orderUseCases.getOrdersUseCase(_state.value.token).onEach { result ->
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
                    result.data?.let {
                        _state.value = _state.value.copy(
                            orders = it
                        )
                    }
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}