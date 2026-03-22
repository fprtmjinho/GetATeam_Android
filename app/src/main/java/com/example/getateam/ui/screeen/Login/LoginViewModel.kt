package com.example.getateam.ui.screeen.Login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.log

class LoginViewModel : ViewModel() {

    var state = mutableStateOf(LoginState())
        private set

    fun onIdChange(newId: String) {
        state.value = state.value.copy(
            id = newId,
            isIdValid = newId.isNotBlank()
        )
    }

    fun onPasswordChange(newPw: String) {
        state.value = state.value.copy(
            password = newPw,
            isPasswordValid = newPw.isNotBlank()
        )
    }

    fun login() {
        val current = state.value

        if (!current.isFormValid) {
            println("유효하지 않음")
            return
        }
        // TODO: 로그인 API 연결
    }
}