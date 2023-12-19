package com.mammates.mammates_buyer_v1.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName


data class OrdersDto(

    @field:SerializedName("orders")
    val orders: List<OrderItemDto>? = null
)

data class OrderItemDto(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("foods")
    val foods: List<FoodOrderItemDto>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("store")
    val store: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)



