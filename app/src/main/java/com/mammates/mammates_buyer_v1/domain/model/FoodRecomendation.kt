package com.mammates.mammates_buyer_v1.domain.model

data class FoodRecommendation(
    val id: Int,
    val name: String,
    val price: Int,
    val storeId: Int,
    val image: String? = null
)
