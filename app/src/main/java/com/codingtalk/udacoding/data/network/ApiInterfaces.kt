package com.codingtalk.udacoding.data.network

import com.codingtalk.udacoding.data.model.login.LoginForm
import com.codingtalk.udacoding.data.model.login.LoginResult
import com.codingtalk.udacoding.data.model.register.RegisterData
import com.codingtalk.udacoding.data.model.register.RegisterResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterfaces {

    @POST("register")
    fun register(
        @Body registerData: RegisterData
    ): Single<RegisterResult>

    @POST("login")
    fun login(
        @Body loginForm: LoginForm
    ): Single<LoginResult>
}