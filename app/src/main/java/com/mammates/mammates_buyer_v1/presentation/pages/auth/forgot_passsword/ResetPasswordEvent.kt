package com.mammates.mammates_buyer_v1.presentation.pages.auth.forgot_passsword

sealed class ResetPasswordEvent {
    data class OnChangeEmail(val email: String) : ResetPasswordEvent()
    data object OnOpenDialog : ResetPasswordEvent()
    data object OnDismissDialog : ResetPasswordEvent()
}