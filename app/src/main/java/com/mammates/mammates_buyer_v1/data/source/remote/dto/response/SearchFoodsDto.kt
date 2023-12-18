package com.mammates.mammates_buyer_v1.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class SearchFoodsDto(

    @field:SerializedName("foods")
    val foods: List<FoodsSearchItem>? = null
)

data class Seller(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class FoodsSearchItem(

    @field:SerializedName("mam_rates")
    val mamRates: Int? = null,

    @field:SerializedName("seller")
    val seller: Seller? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
