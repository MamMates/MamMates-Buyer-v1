package com.mammates.mammates_buyer_v1.presentation.pages.auth.forgot_passsword

import androidx.lifecycle.ViewModel
import com.mammates.mammates_buyer_v1.presentation.util.validation.emailValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.asStateFlow()

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.OnChangeEmail -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    emailValidationResult = emailValidation(event.email)
                )
            }

            ResetPasswordEvent.OnOpenDialog -> {
                _state.value = _state.value.copy(
                    isDialogOpen = true
                )
            }

            ResetPasswordEvent.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    isDialogOpen = false
                )
            }
        }
    }
}