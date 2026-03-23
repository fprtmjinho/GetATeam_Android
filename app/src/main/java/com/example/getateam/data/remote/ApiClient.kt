package com.example.getateam.data.remote

import com.example.getateam.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val baseUrl = BuildConfig.BASE_URL.also {
        require(it.endsWith("/")) { "BASE_URL must end with '/': $it" }
    }
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
}