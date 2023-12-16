package com.mammates.mammates_buyer_v1.presentation.pages.main.home

import androidx.lifecycle.ViewModel
import com.mammates.mammates_buyer_v1.domain.use_case.intro.IntroUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val introUseCases: IntroUseCases,
    private val tokenUseCases: TokenUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getInitialValue()
    }

    fun onEvent(event: HomeEvent) {

    }

    private fun getInitialValue() {
        _state.value = _state.value.copy(
            isOnBoarding = introUseCases.getIntroIsDoneUseCase(),
            token = tokenUseCases.getTokenUseCase()
        )
    }

}