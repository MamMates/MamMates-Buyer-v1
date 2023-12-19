package com.mammates.mammates_buyer_v1.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class FoodOrderItemDto(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("name")
    val name: String? = null
)