package com.codingtalk.udacoding.data.source.register

import com.codingtalk.udacoding.data.model.register.RegisterData
import com.codingtalk.udacoding.data.model.register.RegisterResult
import com.codingtalk.udacoding.data.network.ApiInterfaces
import io.reactivex.rxjava3.core.Single

class RegisterDataSource(private val api: ApiInterfaces) {

    fun submit(data: RegisterData): Single<RegisterResult> {
        return api.register(data)
    }
}