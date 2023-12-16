package com.mammates.mammates_buyer_v1.domain.repository

interface IntroRepository {
    fun getIntroIsDone(): Boolean
    suspend fun setIntroIsDone(isDone: Boolean)
}