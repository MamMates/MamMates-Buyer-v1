package com.mammates.mammates_buyer_v1.presentation.pages.main.account

data class AccountState(
    val fullName: String = "No Data Full Name",
    val token: String = "",
    val isLoading: Boolean = false,
    val profileImage: String? = null,
    val errorMessage: String? = null,
    val isNotAuthorizeDialogOpen: Boolean = false,
    val isRefresh: Boolean = false,
    val isConfirmLogoutOpen: Boolean = false,
)
