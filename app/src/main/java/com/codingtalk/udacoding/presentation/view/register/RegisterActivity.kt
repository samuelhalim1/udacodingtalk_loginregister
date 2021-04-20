package com.codingtalk.udacoding.presentation.view.register

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codingtalk.udacoding.R
import com.codingtalk.udacoding.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity: AppCompatActivity() {

    private val viewModel by viewModel<RegisterViewModel>()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.userFirst.observe(this, {
            viewModel.setFirstName(it)
        })

        viewModel.userLast.observe(this, {
            viewModel.setLastName(it)
        })

        viewModel.userEmail.observe(this, {
            viewModel.setEmail(it)
        })

        viewModel.userPassword.observe(this, {
            viewModel.setPassword(it)
        })

        viewModel.userConfPassword.observe(this, {
            viewModel.setConfPassword(it)
        })

        viewModel.viewFocus.observe(this, {
            hide(it)
        })

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        viewModel.confPassError.observe(this, {
            register_et_conf_pass.error = it
        })

        viewModel.registerFormValidation.observe(this, {
            register_et_firstname.error = it.firstError
            register_et_lastname.error = it.lastError
            register_et_email.error = it.emailError
            register_et_pass.error = it.passError
            register_et_conf_pass.error = it.confPassError
        })

        viewModel.registerResult.observe(this, {
            if(it.isSuccess) {
                makeToast("Sukses mendaftarkan akun!")
                onBackPressed()
            } else {
                makeToast(it.message)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            register_et_firstname.error = null
            register_et_lastname.error = null
            register_et_email.error = null
            register_et_pass.error = null
            register_et_conf_pass.error = null

            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            register_btn.visibility = View.GONE
            register_loading.visibility = View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            register_btn.visibility = View.VISIBLE
            register_loading.visibility = View.GONE
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