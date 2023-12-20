package com.mammates.mammates_buyer_v1.data.repository

import com.mammates.mammates_buyer_v1.data.source.remote.MamMatesApi
import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.OrderBuyerItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqTotal
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderDetailDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrdersDto
import com.mammates.mammates_buyer_v1.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: MamMatesApi
) : OrderRepository {
    override suspend fun getOrders(token: String): ResMamMates<OrdersDto> {
        return api.getOrder(token)
    }

    override suspend fun getOrderDetail(token: String, id: Int): ResMamMates<OrderDetailDto> {
        return api.getOrderDetail(token, id)
    }

    override suspend fun postOrder(
        token: String,
        food: List<OrderBuyerItem>,
        sellerId: Int
    ): ResMamMates<String> {
        return api.postOrder(
            token,
            ReqTotal(
                foods = food,
                sellerId = sellerId,
            )
        )
    }
}