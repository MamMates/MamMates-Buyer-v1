package com.mammates.mammates_buyer_v1.data.repository

import com.mammates.mammates_buyer_v1.data.source.remote.MamMatesApi
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.SearchFoodsDto
import com.mammates.mammates_buyer_v1.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val api: MamMatesApi
) : FoodRepository {
    override suspend fun getSearchFood(
        token: String,
        keywords: String
    ): ResMamMates<SearchFoodsDto> {
        return api.searchFoods(
            token = token,
            keywords = keywords
        )
    }

    override suspend fun getStoreFood(token: String, store: Int): ResMamMates<SearchFoodsDto> {
        return api.searchFoods(
            token = token,
            store = store,
        )
    }

    override suspend fun getFoodRecommendation(token: String): ResMamMates<SearchFoodsDto> {
        return api.getFoodRecommendation(token)
    }

}