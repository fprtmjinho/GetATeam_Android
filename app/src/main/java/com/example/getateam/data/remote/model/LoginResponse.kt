package com.example.getateam.data.remote.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val email: String,
    val githubLink: String,
    val name: String,
    val introduction: String,
    val residence: String,
    val techStackList: List<TechStack>,
    val userId: Int
)