package com.codingtalk.udacoding.presentation.view.main

import androidx.lifecycle.ViewModel
import com.codingtalk.udacoding.data.helper.SessionManager
import com.codingtalk.udacoding.data.model.login.LoginData
import com.google.gson.Gson

class MainViewModel(private val pref: SessionManager): ViewModel() {


    fun checkState(): Boolean {
        return pref.login
    }

    fun getUserData(): LoginData {
        return Gson().fromJson(pref.user, LoginData::class.java)
    }
}