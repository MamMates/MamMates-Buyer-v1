package com.mammates.mammates_buyer_v1.domain.model

import com.mammates.mammates_buyer_v1.util.Rating

data class FoodSearch(
    val id: Int,
    val seller: String,
    val name: String,
    val rating: Rating,
    val price: Int,
    val image: String? = null,
    val sellerId: Int
)
