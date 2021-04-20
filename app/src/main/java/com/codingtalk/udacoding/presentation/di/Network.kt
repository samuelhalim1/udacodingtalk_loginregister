package com.codingtalk.udacoding.presentation.di

import android.util.Log
import com.codingtalk.udacoding.data.network.ApiInterfaces
import com.codingtalk.udacoding.data.network.Constants
import com.codingtalk.udacoding.presentation.utils.prettyJson
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    fun provideRetrofitInstance(): Retrofit {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 2
        val interceptor = HttpLoggingInterceptor(ApiLogger())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants().URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiInterfaces {
        return retrofit.create(ApiInterfaces::class.java)
    }

    class ApiLogger: HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            val logName = "ApiLogger"
            val prettyJson = message.prettyJson()
            Log.d(logName, prettyJson)
        }
    }
}