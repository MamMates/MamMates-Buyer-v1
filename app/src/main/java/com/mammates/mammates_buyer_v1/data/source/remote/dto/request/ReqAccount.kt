package com.mammates.mammates_buyer_v1.data.source.remote.dto.request

import com.google.gson.annotations.SerializedName

data class ReqAccount(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    )