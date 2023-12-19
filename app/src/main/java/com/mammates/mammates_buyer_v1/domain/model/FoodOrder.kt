package com.mammates.mammates_buyer_v1.domain.model

data class FoodOrder(
    val image: String?,
    val quantity: Int,
    val price: Int,
    val name: String
)