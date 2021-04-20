package com.codingtalk.udacoding.data.model.register

data class RegisterForm(
    val first: String,
    val last: String,
    val email: String,
    val pass: String,
    val confPass: String
)