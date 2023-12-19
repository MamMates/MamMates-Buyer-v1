package com.mammates.mammates_buyer_v1.data.source.remote

import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqLogin
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqRegister
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqTotal
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderDetailDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrdersDto
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

    @GET("foods/recommendation")
    suspend fun getFoodRecommendation(
        @Header("Authorization") token: String,
    ) : ResMamMates<SearchFoodsDto>

    // Order
    @GET("orders/buyer")
    suspend fun getOrder(
        @Header("Authorization") token: String,
    ) : ResMamMates<OrdersDto>

    @GET("orders/buyer")
    suspend fun getOrderDetail(
        @Header("Authorization") token: String,
        @Query("id") id : Int? = null,
    ) : ResMamMates<OrderDetailDto>

    @POST("orders/buyer")
    suspend fun postOrder(
        @Header("Authorization") token : String,
        @Body reqTotal: ReqTotal
    ) : ResMamMates<String>

}