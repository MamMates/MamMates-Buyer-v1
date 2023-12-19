package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

import androidx.lifecycle.SavedStateHandle
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
class OrderDetailViewModel @Inject constructor(
    private val tokenUseCases: TokenUseCases,
    private val orderUseCases: OrderUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(OrderDetailState())
    val state = _state.asStateFlow()

    init {
        getToken()

        savedStateHandle.get<Int>("order_id")?.let { id ->
            _state.value = _state.value.copy(
                id = id
            )
        }
    }


    fun onEvent(event: OrderDetailEvent) {
        when (event) {
            OrderDetailEvent.ClearToken -> {
                viewModelScope.launch {
                    tokenUseCases.clearTokenUseCase()
                }
                _state.value = _state.value.copy(
                    token = ""
                )
            }

            OrderDetailEvent.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null
                )
            }

            OrderDetailEvent.OnRefreshPage -> {
                getToken()

                getOrderDetail()
            }
        }
    }

    private fun getToken() {
        _state.value = _state.value.copy(
            token = tokenUseCases.getTokenUseCase()
        )
    }

    private fun getOrderDetail() {
        orderUseCases.getOrderDetailUseCase(
            _state.value.token,
            _state.value.id,
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
                    result.data?.let {
                        _state.value = _state.value.copy(
                            invoice = it.invoice,
                            storeName = it.store,
                            date = it.date,
                            foods = it.foods,
                            status = it.status,
                            total = it.total
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