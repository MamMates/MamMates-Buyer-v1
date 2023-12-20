package com.mammates.mammates_buyer_v1.domain.repository

import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import retrofit2.Response


interface AuthRepository {

    suspend fun authLogin(
        email: String,
        password: String
    ): Response<ResMamMates<String>>

    suspend fun authRegister(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): ResMamMates<String>
}