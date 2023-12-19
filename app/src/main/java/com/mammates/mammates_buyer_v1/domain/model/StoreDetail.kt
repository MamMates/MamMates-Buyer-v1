package com.mammates.mammates_buyer_v1.domain.model

data class StoreDetail(
    val id: Int,
    val seller: String,
    val address: String,
    val foods: List<FoodSearch>,
)
