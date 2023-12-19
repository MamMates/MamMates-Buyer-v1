package com.mammates.mammates_buyer_v1.domain.model

import com.mammates.mammates_buyer_v1.util.StatusOrder

data class Order(
    val total: Int,
    val foods: List<FoodOrder>,
    val id: Int,
    val store: String,
    val status: StatusOrder
)

