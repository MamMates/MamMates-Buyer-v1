package com.mammates.mammates_buyer_v1.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class AccountDto(

    @field:SerializedName("account")
    val account: AccountItemsDto? = null

)

data class AccountItemsDto(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("image")
    val image: String? = null
)
