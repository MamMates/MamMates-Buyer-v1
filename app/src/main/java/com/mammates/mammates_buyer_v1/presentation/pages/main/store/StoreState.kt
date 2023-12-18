package com.mammates.mammates_buyer_v1.presentation.pages.main.store

data class StoreState(
    val quantity : Map<Int, Int> = mutableMapOf()
)
