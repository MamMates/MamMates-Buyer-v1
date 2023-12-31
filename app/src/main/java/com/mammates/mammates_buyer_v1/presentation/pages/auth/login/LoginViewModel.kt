package com.mammates.mammates_buyer_v1.presentation.pages.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.domain.use_case.auth.AuthUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import com.mammates.mammates_buyer_v1.presentation.util.validation.emailValidation
import com.mammates.mammates_buyer_v1.presentation.util.validation.passwordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tokenUseCases: TokenUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnChangeEmail -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailValidationResult = emailValidation(event.email)
                )
            }

            is LoginEvent.OnChangePassword -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordValidationResult = passwordValidation(event.password)
                )
            }

            LoginEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }

            LoginEvent.OnLogin -> {

                validateAllFieldValue()

                if (
                    !_state.value.emailValidationResult.isNullOrEmpty() &&
                    !_state.value.passwordValidationResult.isNullOrEmpty()
                ) {
                    return
                }

                loginUser()

            }

            LoginEvent.OnDismissErrorDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null,
                )
            }
        }
    }

    private fun validateAllFieldValue() {
        _state.value = _state.value.copy(
            emailValidationResult = emailValidation(_state.value.email),
            passwordValidationResult = passwordValidation(_state.value.password)
        )
    }

    private fun loginUser() {
        authUseCases.authLoginUseCase(_state.value.email, _state.value.password)
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
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
                            tokenUseCases.setTokenUseCase(it)
                            _state.value = _state.value.copy(
                                isAuth = true,
                                isLoading = false
                            )
                        }
                    }
                }

            }.launchIn(viewModelScope)
    }


}