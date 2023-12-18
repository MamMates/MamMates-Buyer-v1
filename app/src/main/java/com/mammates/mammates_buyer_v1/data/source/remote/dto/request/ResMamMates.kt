package com.mammates.mammates_buyer_v1.data.source.remote.dto.request

import com.google.gson.annotations.SerializedName

data class ResMamMates<T>(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: T? = null,
)