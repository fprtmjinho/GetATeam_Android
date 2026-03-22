package com.example.getateam.ui.screeen.Login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getateam.data.repository.AuthRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

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
            state.value = current.copy(loginError = "아이디/비밀번호를 입력해주세요")
            return
        }

        viewModelScope.launch {
            state.value = current.copy(isLoading = true, loginError = null)
            val result = repository.login(current.id, current.password)
            result.fold(
                onSuccess = {
                    state.value = state.value.copy(isLoading = false, loginSuccess = true)
                },
                onFailure = { error ->
                    state.value = state.value.copy(isLoading = false, loginError = error.message ?: "로그인 실패")
                }
            )
        }
    }
}