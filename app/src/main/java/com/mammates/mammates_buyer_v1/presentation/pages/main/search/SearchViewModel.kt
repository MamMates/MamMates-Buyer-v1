package com.mammates.mammates_buyer_v1.presentation.pages.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.domain.model.FoodSearch
import com.mammates.mammates_buyer_v1.domain.use_case.food.FoodUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import com.mammates.mammates_buyer_v1.util.HttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val foodUseCases: FoodUseCases,
    private val tokenUseCases: TokenUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _keywords = MutableStateFlow("")
    val keywords = _keywords.asStateFlow()

    private val _foods = MutableStateFlow(listOf<FoodSearch>())
    val foods = keywords
        .debounce(500L)
        .combine(_foods) { keywords, food ->
            if (keywords.isBlank()) {
                food
            } else {
                getSearchItem(keywords)
                food
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _foods.value
        )

    init {
        getTokenValue()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchItem -> {
                _keywords.value = event.keywords
            }

            SearchEvent.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null
                )
            }

            SearchEvent.ClearToken -> {
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

    private fun getSearchItem(keywords: String) {
        foodUseCases.getSearchFoodUseCase(
            token = _state.value.token,
            keywords = keywords
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
                    _foods.value = result.data ?: emptyList()
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

}