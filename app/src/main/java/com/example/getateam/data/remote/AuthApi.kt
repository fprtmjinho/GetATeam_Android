package com.example.getateam.data.remote

import com.example.getateam.data.remote.model.BaseResponse
import com.example.getateam.data.remote.model.LoginRequest
import com.example.getateam.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<BaseResponse<LoginResponse>>
}