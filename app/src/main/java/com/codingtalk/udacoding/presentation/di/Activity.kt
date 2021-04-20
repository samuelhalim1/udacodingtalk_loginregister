package com.codingtalk.udacoding.presentation.di

import android.app.Application
import com.codingtalk.udacoding.presentation.di.AppModule.loginModule
import com.codingtalk.udacoding.presentation.di.AppModule.mainModule
import com.codingtalk.udacoding.presentation.di.AppModule.prefModule
import com.codingtalk.udacoding.presentation.di.AppModule.registerModule
import com.codingtalk.udacoding.presentation.di.AppModule.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Activity: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@Activity)
            modules(
                listOf(
                    prefModule,
                    retrofitModule,
                    registerModule,
                    loginModule,
                    mainModule
                )
            )
        }
    }
}