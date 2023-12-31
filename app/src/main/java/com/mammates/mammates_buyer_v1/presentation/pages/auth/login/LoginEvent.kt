package com.mammates.mammates_buyer_v1.presentation.pages.auth.login

sealed class LoginEvent {
    data class OnChangeEmail(val email: String) : LoginEvent()
    data class OnChangePassword(val password: String) : LoginEvent()
    data object TogglePasswordVisibility : LoginEvent()
    data object OnDismissErrorDialog : LoginEvent()
    data object OnLogin : LoginEvent()

}