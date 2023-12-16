package com.mammates.mammates_buyer_v1.presentation.pages.on_boarding.component

import com.mammates.mammates_buyer_v1.R


data class OnBoardingPagerItem(
    val title: String,
    val description: String,
    val drawableRes: Int
)

val onBoardingPagerItem = listOf(
    OnBoardingPagerItem(
        title = "Serve Kindness, Reduce Food Waste!",
        description = "Explore MamMates: connecting kindness and eco-awareness with affordable, quality food to combat waste. Join us to enjoy delicious eats and support sustainability.",
        drawableRes = R.drawable.pager_service
    ),
    OnBoardingPagerItem(
        title = "Buy Quality Food at Special Prices!",
        description = "Explore various flavors with MamMates, an innovative application that provides high quality leftover food at affordable prices. Join in the positive change in our view of food waste.",
        drawableRes = R.drawable.pager_price
    ),
    OnBoardingPagerItem(
        title = "Explore Delish, Lessen Environmental Impact!",
        description = "Culinary delights and environmental awareness. Every purchase reduces food waste and supports sustainability. Let's, from now on, contribute to change in the culinary world!",
        drawableRes = R.drawable.pager_environment
    ),
)

