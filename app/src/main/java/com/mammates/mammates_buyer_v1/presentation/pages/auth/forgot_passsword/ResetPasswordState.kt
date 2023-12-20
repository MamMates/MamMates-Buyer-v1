package com.mammates.mammates_buyer_v1.presentation.pages.auth.forgot_passsword

data class ResetPasswordState(
    val email: String = "",
    val emailValidationResult: String? = null,
    val isDialogOpen: Boolean = false
)