package com.mammates.mammates_buyer_v1.domain.repository

import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates


interface AuthRepository {

    suspend fun authLogin(
        email: String,
        password: String
    ): ResMamMates<String>

    suspend fun authRegister(
        buyer: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): ResMamMates<String>
}