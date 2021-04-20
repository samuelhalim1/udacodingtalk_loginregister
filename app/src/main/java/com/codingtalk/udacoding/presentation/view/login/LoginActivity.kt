package com.codingtalk.udacoding.presentation.view.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codingtalk.udacoding.R
import com.codingtalk.udacoding.databinding.ActivityLoginBinding
import com.codingtalk.udacoding.presentation.view.main.MainActivity
import com.codingtalk.udacoding.presentation.view.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.userEmail.observe(this, {
            viewModel.setEmail(it)
        })

        viewModel.userPass.observe(this, {
            viewModel.setPassword(it)
        })

        viewModel.viewFocus.observe(this, {
            hide(it)
        })

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        viewModel.gotoRegister.observe(this, {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
        })

        viewModel.loginFormValidation.observe(this, {
            login_et_email.error = it.emailError
            login_et_pass.error = it.passwordError
        })

        viewModel.loginResult.observe(this, {
            if(it.isSuccess) {
                makeToast("Login sukses")
                moveToMain(it.data!!.first_name)
            } else {
                makeToast(it.message)
            }
        })
    }

    private fun moveToMain(firstName: String) {
        makeToast("Welcome, $firstName")
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )

        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            login_et_email.error = null
            login_et_pass.error = null

            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            login_btn.visibility = View.GONE
            login_loading.visibility = View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            login_btn.visibility = View.VISIBLE
            login_loading.visibility = View.GONE
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun hide(v: View) {
        val input = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(v.windowToken, 0)
    }
}