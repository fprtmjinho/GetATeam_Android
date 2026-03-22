package com.example.getateam.ui.screeen.Login

data class LoginState(
    val id: String = "",
    val password: String = "",
    val isIdValid: Boolean = false,
    val isPasswordValid: Boolean = false
){
    val isFormValid: Boolean
        get() = isIdValid && isPasswordValid
}
