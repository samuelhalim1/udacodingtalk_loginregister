package com.codingtalk.udacoding.data.model.login

data class LoginResult(
    val isSuccess: Boolean,
    val message: String,
    val data: LoginData? = null
)
