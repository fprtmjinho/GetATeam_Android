package com.example.getateam.data.repository

import android.content.Context
import com.example.getateam.data.remote.ApiClient
import com.example.getateam.data.remote.model.LoginRequest
import com.example.getateam.data.remote.model.LoginResponse
import androidx.datastore.preferences.preferencesDataStore
import com.example.getateam.data.remote.model.BaseResponse
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AuthRepository(private val context: Context) {
    private val api = ApiClient.authApi

    private val sharedPrefs by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            "secure_user_prefs",     // 파일 이름
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    suspend fun login(id: String, password: String): Result<BaseResponse<LoginResponse>> {
        return try {
            val response = api.login(LoginRequest(id, password))
            if (response.isSuccessful) {
                val body = response.body() ?: return Result.failure(
                    IllegalStateException("로그인 응답 본문이 비어있습니다. (HTTP ${response.code()})")
                )
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
    private fun saveTokens(accessToken: String, refreshToken: String, userId: Int) {
        sharedPrefs.edit().apply {
            putString("access_token", accessToken)
            putString("refresh_token", refreshToken)
            putInt("user_id", userId)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPrefs.getString("access_token", null)
    }

    fun getRefreshToken(): String? {
        return sharedPrefs.getString("refresh_token", null)
    }

    fun getUserId(): Int {
        return sharedPrefs.getInt("user_id", -1)
    }
}