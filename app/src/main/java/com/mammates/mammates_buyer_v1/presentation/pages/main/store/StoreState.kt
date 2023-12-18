package com.mammates.mammates_buyer_v1.presentation.pages.main.store

import com.mammates.mammates_buyer_v1.domain.model.FoodItem
import com.mammates.mammates_buyer_v1.util.Rating

data class StoreState(
    val quantity: Map<Int, Int> = mutableMapOf(),
    val total: Int = 0,
    val foods: List<FoodItem> = listOf(
        FoodItem(
            id = 1,
            price = 5000,
            name = "Donut 1",
            rating = Rating.THREE,
            seller = "Toko Tude"
        ),
        FoodItem(
            id = 2,
            price = 6000,
            name = "Donut 2",
            rating = Rating.THREE,
            seller = "Toko Tude"
        ),
        FoodItem(
            id = 3,
            price = 7000,
            name = "Donut 3",
            rating = Rating.THREE,
            seller = "Toko Tude"
        ),
        FoodItem(
            id = 4,
            price = 5000,
            name = "Donut 4",
            rating = Rating.THREE,
            seller = "Toko Tude"
        ),
    )
)
