package com.mammates.mammates_buyer_v1.domain.repository

import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.SearchFoodsDto

interface FoodRepository {
    suspend fun getSearchFood(
        token: String,
        keywords: String,
    ): ResMamMates<SearchFoodsDto>

    suspend fun getStoreFood(
        token: String,
        store: Int,
    ): ResMamMates<SearchFoodsDto>

    suspend fun getFoodRecommendation(
        token: String
    ): ResMamMates<SearchFoodsDto>
}