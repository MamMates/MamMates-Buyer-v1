package com.mammates.mammates_buyer_v1.domain.model

import com.mammates.mammates_buyer_v1.util.StatusOrder

data class OrderDetail(
    val invoice : String,
    val store : String,
    val date : String,
    val foods : List<FoodOrder>,
    val total : Int,
    val  status : StatusOrder
)
