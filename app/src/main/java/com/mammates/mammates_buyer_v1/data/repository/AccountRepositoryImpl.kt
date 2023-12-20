package com.mammates.mammates_buyer_v1.data.repository

import com.mammates.mammates_buyer_v1.data.source.remote.MamMatesApi
import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqAccount
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqPasswordChange
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.AccountDto
import com.mammates.mammates_buyer_v1.domain.repository.AccountRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: MamMatesApi
) : AccountRepository {
    override suspend fun getAccount(token: String): ResMamMates<AccountDto> {
        return api.getAccount(token)
    }

    override suspend fun updateAccount(
        token: String,
        name: String,
        email: String
    ): ResMamMates<String> {
        return api.updateAccount(
            token = token,
            reqAccount = ReqAccount(
                name, email
            )
        )
    }

    override suspend fun updateProfilePicture(
        token: String,
        image: MultipartBody.Part
    ): ResMamMates<String> {
        return api.updateProfilePicture(token, image)
    }

    override suspend fun changePassword(
        token: String,
        oldPassword: String,
        newPassword: String,
        newPasswordConfirm: String
    ): ResMamMates<String> {
        return api.changePassword(
            token = token,
            reqPasswordChange = ReqPasswordChange(
                oldPassword = oldPassword,
                newPassword = newPassword,
                newPasswordRepeat = newPasswordConfirm
            )
        )
    }

}