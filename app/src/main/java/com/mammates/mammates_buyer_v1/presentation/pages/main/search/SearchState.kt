package com.mammates.mammates_buyer_v1.presentation.pages.main.search

data class SearchState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val token: String = "",
    val isNotAuthorizeDialogOpen: Boolean = false,
)
