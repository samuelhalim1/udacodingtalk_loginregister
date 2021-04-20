package com.codingtalk.udacoding.data.source.login

import com.codingtalk.udacoding.data.model.login.LoginForm
import com.codingtalk.udacoding.data.model.login.LoginResult
import com.codingtalk.udacoding.data.network.ApiInterfaces
import io.reactivex.rxjava3.core.Single

class LoginDataSource(private val api: ApiInterfaces) {

    fun login(data: LoginForm): Single<LoginResult> {
        return api.login(data)
    }
}