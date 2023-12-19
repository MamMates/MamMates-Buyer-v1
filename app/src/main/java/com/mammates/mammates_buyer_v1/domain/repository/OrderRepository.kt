package com.mammates.mammates_buyer_v1.domain.repository

import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.OrderBuyerItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderDetailDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrdersDto

interface OrderRepository {
    suspend fun getOrders(
        token: String
    ): ResMamMates<OrdersDto>

    suspend fun getOrderDetail(
        token: String,
        id: Int
    ): ResMamMates<OrderDetailDto>

    suspend fun postOrder (
        token: String,
        food : List<OrderBuyerItem>,
        sellerId : Int
    ) : ResMamMates<String>
}