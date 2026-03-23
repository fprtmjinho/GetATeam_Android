package com.example.getateam.data.remote.model

data class BaseResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val data: T?
)