package com.example.getateam.ui.screeen.Login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var state = mutableStateOf(LoginState())
        private set

    fun onIdChange(newId: String) {
        state.value = state.value.copy(id = newId)
    }

    fun onPasswordChange(newPw: String) {
        state.value = state.value.copy(password = newPw)
    }

    fun login() {
        // TODO: API 연결
    }
}