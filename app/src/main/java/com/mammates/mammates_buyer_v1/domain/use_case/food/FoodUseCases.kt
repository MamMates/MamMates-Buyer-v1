package com.mammates.mammates_buyer_v1.domain.use_case.food

data class FoodUseCases(
    val getSearchFoodUseCase: GetSearchFoodUseCase,
    val getStoreFoodUseCase: GetStoreFoodUseCase,
    val getFoodRecommendationUseCase: GetFoodRecommendationUseCase
)
