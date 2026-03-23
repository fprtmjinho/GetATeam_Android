package com.example.getateam.data.repository

import android.content.Context
import android.util.Log
import com.example.getateam.data.remote.ApiClient
import com.example.getateam.data.remote.model.LoginRequest
import com.example.getateam.data.remote.model.LoginResponse
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.getateam.data.remote.model.BaseResponse
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("user_prefs")
class AuthRepository(private val context: Context) {
    private val api = ApiClient.authApi

    suspend fun login(id: String, password: String): Result<BaseResponse<LoginResponse>> {
        return try {
            val response = api.login(LoginRequest(id, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                if(body.isSuccess && body.data != null){
                    val data = body.data!!
                    saveTokens(data.accessToken, data.refreshToken, data.userId)
                    Result.success(body)
                } else {
                    Result.failure(Exception("로그인 실패: ${body.message}"))
                }
            } else {
                Result.failure(Exception("로그인 실패: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    private suspend fun saveTokens(accessToken: String, refreshToken: String, userId: Int) {
        val accessKey = stringPreferencesKey("access_token")
        val refreshKey = stringPreferencesKey("refresh_token")
        val userKey = intPreferencesKey("user_id")
        context.dataStore.edit { prefs ->
            prefs[accessKey] = accessToken
            prefs[refreshKey] = refreshToken
            prefs[userKey] = userId
        }
    }
    suspend fun getAccessToken(): String? {
        val accessKey = stringPreferencesKey("access_token")
        return context.dataStore.data.first()[accessKey]
    }
}