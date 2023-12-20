package com.mammates.mammates_buyer_v1.presentation.pages.auth.register

sealed class RegisterEvent {
    data class OnChangeName(val name: String) : RegisterEvent()
    data class OnChangeEmail(val email: String) : RegisterEvent()
    data class OnChangePassword(val password: String) : RegisterEvent()
    data class OnChangeConfirmPassword(val confirmPassword: String) : RegisterEvent()
    data object TogglePasswordVisibility : RegisterEvent()
    data object TogglePasswordConfirmVisibility : RegisterEvent()

    data object OnRegister : RegisterEvent()

    data object OnDismissDialogError : RegisterEvent()
    data object OnDismissDialogSuccess : RegisterEvent()


}