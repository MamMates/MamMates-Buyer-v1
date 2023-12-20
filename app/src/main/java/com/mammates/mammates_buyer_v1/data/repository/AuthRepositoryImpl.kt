package com.mammates.mammates_buyer_v1.data.repository

import com.mammates.mammates_buyer_v1.data.source.remote.MamMatesApi
import com.mammates.mammates_buyer_v1.data.source.remote.dto.ResMamMates
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqLogin
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.ReqRegister
import com.mammates.mammates_buyer_v1.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: MamMatesApi
) : AuthRepository {
    override suspend fun authLogin(email: String, password: String): Response<ResMamMates<String>> {
        return api.authLogin(
            reqLogin = ReqLogin(email, password)
        )
    }

    override suspend fun authRegister(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): ResMamMates<String> {
        return api.authRegister(
            reqRegister = ReqRegister(
                name = name,
                email = email,
                password = password,
                passwordRepeat = passwordConfirm
            )
        )
    }


}
