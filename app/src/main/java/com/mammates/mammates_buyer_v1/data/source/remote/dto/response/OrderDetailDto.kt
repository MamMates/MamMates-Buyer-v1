package com.mammates.mammates_buyer_v1.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class OrderDetailDto(

	@field:SerializedName("order")
	val order: OrderDto? = null
)

data class OrderDto(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("foods")
	val foods: List<FoodOrderItemDto>? = null,

	@field:SerializedName("invoice")
	val invoice: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("store")
	val store: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

