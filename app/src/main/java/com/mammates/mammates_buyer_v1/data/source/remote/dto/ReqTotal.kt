package com.mammates.mammates_buyer_v1.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class ReqTotal(

	@field:SerializedName("orders")
	val orders: List<OrdersBuyerItem?>? = null,

	@field:SerializedName("seller_id")
	val sellerId: Int? = null
)

data class OrdersBuyerItem(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("food_id")
	val foodId: Int? = null
)
