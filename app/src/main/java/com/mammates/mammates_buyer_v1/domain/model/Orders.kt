package com.mammates.mammates_buyer_v1.domain.model

import com.google.gson.annotations.SerializedName


data class Orders(

    @field:SerializedName("orders")
    val orders: List<OrderItem>? = null
)

data class OrderItem(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("foods")
    val foods: List<FoodOrderItem>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("store")
    val store: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

