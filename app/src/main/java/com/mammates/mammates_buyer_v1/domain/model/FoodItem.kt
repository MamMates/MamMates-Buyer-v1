package com.mammates.mammates_buyer_v1.domain.model

import com.mammates.mammates_buyer_v1.util.Rating

data class FoodItem(
    val id: Int,
    val seller: String,
    val name: String,
    val rating: Rating,
    val price: Int,
    val image: String? = null,
)
