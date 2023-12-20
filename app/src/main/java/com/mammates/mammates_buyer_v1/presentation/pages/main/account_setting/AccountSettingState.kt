package com.mammates.mammates_buyer_v1.presentation.pages.main.account_setting

import android.net.Uri

data class AccountSettingState(
    val fullName: String = "",
    val fullNameValidation: String? = null,


    val email: String = "",
    val emailValidation: String? = null,

    val profileImageUri: Uri = Uri.EMPTY,
    val profileImageUrl: String? = null,

    val token: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,

    val isConfirmDialogOpen: Boolean = false,
    val isNotAuthorizeDialogOpen: Boolean = false,
)
