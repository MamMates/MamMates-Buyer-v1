package com.mammates.mammates_buyer_v1.presentation.pages.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.domain.use_case.auth.AuthUseCases
import com.mammates.mammates_buyer_v1.presentation.util.validation.emailValidation
import com.mammates.mammates_buyer_v1.presentation.util.validation.emptyValidation
import com.mammates.mammates_buyer_v1.presentation.util.validation.passwordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnChangeConfirmPassword -> {
                _state.value = _state.value.copy(
                    passwordConfirm = event.confirmPassword,
                    passwordConfirmValidationResult = if (_state.value.password != event.confirmPassword) {
                        "Password doesn't match"
                    } else {
                        passwordValidation(event.confirmPassword)
                    }
                )
            }

            is RegisterEvent.OnChangeEmail -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailValidationResult = emailValidation(
                        event.email
                    )
                )
            }

            is RegisterEvent.OnChangeName -> {
                _state.value = _state.value.copy(
                    name = event.name,
                    nameValidationResult = emptyValidation(
                        label = "Name",
                        value = event.name
                    )
                )
            }

            is RegisterEvent.OnChangePassword -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordValidationResult = passwordValidation(
                        event.password
                    ),
                    passwordConfirmValidationResult = if (_state.value.password != _state.value.passwordConfirm) {
                        "Password doesn't match"
                    } else {
                        passwordValidation(_state.value.passwordConfirm)
                    }
                )
            }

            RegisterEvent.TogglePasswordConfirmVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordConfirmVisible = !_state.value.isPasswordConfirmVisible
                )
            }

            RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }

            RegisterEvent.OnDismissDialogError -> {
                _state.value = _state.value.copy(
                    errorMessage = null
                )
            }

            RegisterEvent.OnDismissDialogSuccess -> {
                _state.value = _state.value.copy(
                    successMessage = null
                )
            }

            RegisterEvent.OnRegister -> {
                validateAllFieldValue()

                if (
                    !_state.value.nameValidationResult.isNullOrEmpty() &&
                    !_state.value.emailValidationResult.isNullOrEmpty() &&
                    !_state.value.passwordValidationResult.isNullOrEmpty() &&
                    !_state.value.passwordConfirmValidationResult.isNullOrEmpty()
                ) {
                    return
                }

                registerUser()
            }
        }
    }

    private fun validateAllFieldValue() {
        _state.value = _state.value.copy(
            nameValidationResult = emptyValidation(
                label = "Name",
                value = _state.value.name
            ),
            emailValidationResult = emailValidation(
                _state.value.email
            ),
            passwordValidationResult = passwordValidation(
                _state.value.password
            ),
            passwordConfirmValidationResult = if (_state.value.password != _state.value.passwordConfirm) {
                "Password doesn't match"
            } else {
                passwordValidation(_state.value.passwordConfirm)
            }
        )
    }

    private fun registerUser() {
        authUseCases.authRegisterUseCase(
            name = _state.value.name,
            email = _state.value.email,
            password = _state.value.password,
            passwordConfirm = _state.value.passwordConfirm
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        errorMessage = result.message,
                        isLoading = false,
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
}