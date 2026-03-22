package com.example.getateam.data.repository

import android.content.Context
import com.example.getateam.data.remote.ApiClient
import com.example.getateam.data.remote.model.LoginRequest
import com.example.getateam.data.remote.model.LoginResponse
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("user_prefs")
class AuthRepository(private val context: Context) {
    private val api = ApiClient.authApi

    suspend fun login(id: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(id, password))

            if (response.isSuccessful) {
                val body = response.body()!!
                // 로그인 성공 시 토큰 저장
                saveTokens(body.accessToken, body.refreshToken, body.userId)
                Result.success(body)
            } else {
                Result.failure(Exception("로그인 실패"))
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