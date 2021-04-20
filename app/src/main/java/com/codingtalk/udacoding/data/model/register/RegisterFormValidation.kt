package com.codingtalk.udacoding.data.model.register

data class RegisterFormValidation(
    val firstError: String? = null,
    val lastError: String? = null,
    val emailError: String? = null,
    val passError: String? = null,
    val confPassError: String? = null
)
