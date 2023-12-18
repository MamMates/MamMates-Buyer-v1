package com.mammates.mammates_buyer_v1.presentation.pages.main.search

data class SearchState(
//    val keywords: String = "",
    val isLoading: Boolean = false,
//    val foods: List<FoodItem>? = null,
    val errorMessage: String? = null,
    val token: String = ""
)
