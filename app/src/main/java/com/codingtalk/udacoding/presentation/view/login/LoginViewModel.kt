package com.codingtalk.udacoding.presentation.view.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingtalk.udacoding.data.helper.SessionManager
import com.codingtalk.udacoding.data.model.login.LoginData
import com.codingtalk.udacoding.data.model.login.LoginForm
import com.codingtalk.udacoding.data.model.login.LoginFormValidation
import com.codingtalk.udacoding.data.model.login.LoginResult
import com.codingtalk.udacoding.data.source.login.LoginDataSource
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(private val session: SessionManager, private val loginDataSource: LoginDataSource): ViewModel() {

    private var email = ""
    private var password = ""

    private val _viewFocus = MutableLiveData<View>()
    val viewFocus: LiveData<View> = _viewFocus

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginFormValidation = MutableLiveData<LoginFormValidation>()
    val loginFormValidation: LiveData<LoginFormValidation> = _loginFormValidation

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _gotoRegister = MutableLiveData<Boolean>()
    val gotoRegister: LiveData<Boolean> = _gotoRegister

    val userEmail = MutableLiveData<String>()
    val userPass = MutableLiveData<String>()
    val isBtnEnabled = MutableLiveData<Boolean>()

    fun setEmail(email: String) {
        this.email = email
        isBtnEnabled()
    }

    fun setPassword(pass: String) {
        this.password = pass
        isBtnEnabled()
    }

    fun login(v: View) {
        _viewFocus.value = v
        setLoading(true)
        val data = LoginForm(
            email,
            password
        )

        val validate = validateForm(data)

        if(validate.emailError == null && validate.passwordError == null) {
            loginDataSource.login(
                data
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        setLoading(false)
                        it.data?.let { data ->
                            setSession(
                                data
                            )
                        }
                        _loginResult.value = it
                    },
                    {
                        setLoading(false)
                        _loginResult.value = LoginResult(
                            false,
                            it.localizedMessage!!
                        )
                    }
                )
        } else {
            setLoading(false)
            _loginFormValidation.value = validate
        }
    }

    private fun setSession(user: LoginData) {
        session.login = true
        session.user = Gson().toJson(
            user
        )
    }

    private fun validateForm(data: LoginForm): LoginFormValidation {
        var emailError: String? = null
        var passwordError: String? = null

        if(!checkEmail(data.email)) emailError = "Format email tidak valid!"
        if(data.password.length < 6) passwordError = "Kata Sandi harus minimal 6 huruf"

        return LoginFormValidation(
            emailError, passwordError
        )
    }

    private fun checkEmail(email: String?): Boolean {
        val MAIL_REGEX = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

        return !email.isNullOrBlank() && Regex(MAIL_REGEX).matches(email)
    }

    private fun isBtnEnabled() {
        isBtnEnabled.value =
            email.isNotEmpty() &&
                    password.isNotEmpty()
    }

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun getFocusListener(): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) _viewFocus.value = v
        }
    }

    fun gotoRegister() {
        _gotoRegister.value = true
    }
}