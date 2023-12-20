package com.mammates.mammates_buyer_v1.domain.repository


import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.AccountDto
import okhttp3.MultipartBody

interface AccountRepository {

    suspend fun getAccount(
        token: String,
    ): ResMamMates<AccountDto>

    suspend fun updateAccount(
        token: String,
        name: String,
        email: String
    ): ResMamMates<String>

    suspend fun updateProfilePicture(
        token: String,
        image: MultipartBody.Part,
    ): ResMamMates<String>

    suspend fun changePassword(
        token: String,
        oldPassword: String,
        newPassword: String,
        newPasswordConfirm: String,
    ): ResMamMates<String>

}