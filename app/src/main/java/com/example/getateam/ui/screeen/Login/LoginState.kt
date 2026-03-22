package com.example.getateam.ui.screeen.Login

data class LoginState(
    val id: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val isIdValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val loginSuccess: Boolean = false
){
    val isFormValid: Boolean
        get() = isIdValid && isPasswordValid
}
