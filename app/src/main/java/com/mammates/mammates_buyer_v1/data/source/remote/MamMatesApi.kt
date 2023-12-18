package com.mammates.mammates_buyer_v1.data.source.remote

import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqLogin
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqRegister
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.SearchFoodsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    // Foods
    @GET("foods/find")
    suspend fun searchFoods(
        @Header("Authorization") token: String,
        @Query("q") keywords: String? = null,
        @Query("s") store: Int? = null,
    ): ResMamMates<SearchFoodsDto>


}