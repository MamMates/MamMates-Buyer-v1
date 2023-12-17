package com.mammates.mammates_buyer_v1.data.source.remote

import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import com.mammates.mammates_seller_v1.data.source.remote.dto.ReqLogin
import com.mammates.mammates_seller_v1.data.source.remote.dto.ReqRegister
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MamMatesApi {

    // Auth
    @POST("auth/login")
    suspend fun authLogin(
        @Body reqLogin: ReqLogin
    ): Response<ResMamMates<String>>

    @POST("auth/register/buyer")
    suspend fun authRegister(
        @Body reqRegister: ReqRegister
    ): ResMamMates<String>
}