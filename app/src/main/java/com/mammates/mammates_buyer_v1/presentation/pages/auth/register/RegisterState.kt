package com.mammates.mammates_buyer_v1.presentation.pages.auth.register

data class RegisterState(

    val name: String = "",
    val nameValidationResult: String? = null,

    val email: String = "",
    val emailValidationResult: String? = null,

    val password: String = "",
    val passwordValidationResult: String? = null,
    val isPasswordVisible: Boolean = false,

    val passwordConfirm: String = "",
    val passwordConfirmValidationResult: String? = null,
    val isPasswordConfirmVisible: Boolean = false,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
