package com.codingtalk.udacoding.data.helper

import android.content.SharedPreferences

class SessionManager(private val pref: SharedPreferences) {

    val loginState = "codingTalkLoginState"
    val userData = "codingTalkUserData"

    var login: Boolean
        get() = pref.getBoolean(loginState, false)
        set(loginValue) {
            val editor = pref.edit()
            editor.putBoolean(loginState, loginValue)
            editor.apply()
        }

    var user: String?
        get() = pref.getString(userData, null)
        set(userValue) {
            val editor = pref.edit()
            editor.putString(userData, userValue)
            editor.apply()
        }
}