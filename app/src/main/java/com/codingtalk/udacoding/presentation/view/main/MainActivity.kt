package com.codingtalk.udacoding.presentation.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingtalk.udacoding.R
import com.codingtalk.udacoding.data.helper.SessionManager
import com.codingtalk.udacoding.presentation.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkSession()
    }

    private fun checkSession() {
        if(viewModel.checkState()) {
            initComponents()
        } else {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }

    private fun initComponents() {

        val user = viewModel.getUserData()
        welcome.text = "Hi, ${user.first_name} ${user.last_name}"
    }
}