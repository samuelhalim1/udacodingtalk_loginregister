package com.codingtalk.udacoding.presentation.view.register

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingtalk.udacoding.data.model.register.RegisterData
import com.codingtalk.udacoding.data.model.register.RegisterForm
import com.codingtalk.udacoding.data.model.register.RegisterFormValidation
import com.codingtalk.udacoding.data.model.register.RegisterResult
import com.codingtalk.udacoding.data.source.register.RegisterDataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RegisterViewModel(private val registerDataSource: RegisterDataSource): ViewModel() {

    private var first = ""
    private var last = ""
    private var email = ""
    private var password = ""
    private var confPassword = ""

    private val _viewFocus = MutableLiveData<View>()
    val viewFocus: LiveData<View> = _viewFocus

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _confPassError = MutableLiveData<String?>()
    val confPassError: LiveData<String?> = _confPassError

    private val _registerFormValidation = MutableLiveData<RegisterFormValidation>()
    val registerFormValidation: LiveData<RegisterFormValidation> = _registerFormValidation

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    val userFirst = MutableLiveData<String>()
    val userLast = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userConfPassword = MutableLiveData<String>()
    val isBtnEnabled = MutableLiveData<Boolean>()

    fun setFirstName(first: String) {
        this.first = first
        isBtnEnabled()
    }

    fun setLastName(last: String) {
        this.last = last
        isBtnEnabled()
    }

    fun setEmail(email: String) {
        this.email = email
        isBtnEnabled()
    }

    fun setPassword(pass: String) {
        this.password = pass
        isBtnEnabled()
        checkConfPass()
    }

    fun setConfPassword(confPass: String) {
        this.confPassword = confPass
        isBtnEnabled()
        checkConfPass()
    }

    fun saveData(v: View) {
        _viewFocus.value = v
        setLoading(true)
        val data = RegisterForm(
            first,
            last,
            email,
            password,
            confPassword
        )

        val validate = validateForm(data)

        if(validate.firstError == null &&
                validate.lastError == null &&
                validate.emailError == null &&
                validate.passError == null &&
                validate.confPassError == null) {

            registerDataSource.submit(
                RegisterData(
                    data.email,
                    data.first,
                    data.last,
                    data.pass
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        setLoading(false)
                        _registerResult.value = it
                    },
                    {
                        setLoading(false)
                        _registerResult.value =
                            RegisterResult(
                                false,
                                it.localizedMessage!!
                            )
                    }
                )

        } else {
            setLoading(false)
            _registerFormValidation.value = validate
        }
    }

    private fun validateForm(data: RegisterForm): RegisterFormValidation {
        var firstError: String? = null
        var lastError: String? = null
        var emailError: String? = null
        var passwordError: String? = null
        var confPasswordError: String? = null

        if(data.first.length < 4) firstError = "Nama depan harus lebih dari 3 huruf"
        if(data.last.length < 4) lastError = "Nama belakang harus lebih dari 3 huruf"
        if(!checkEmail(data.email)) emailError = "Format email tidak valid!"
        if(data.pass.length < 6) passwordError = "Kata Sandi harus minimal 6 huruf"
        if(data.confPass.length < 6) confPasswordError = "Konfirmasi Kata Sandi harus minimal 6 huruf"

        checkConfPass()
        return RegisterFormValidation(
            firstError, lastError, emailError, passwordError, confPasswordError
        )
    }

    private fun checkConfPass() {
        if(password.isNotEmpty() && confPassword.isNotEmpty() && password != confPassword) {
            _confPassError.value = "Kata sandi dan konfirmasi tidak sama"
        } else {
            _confPassError.value = null
        }
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
            first.isNotEmpty() &&
            last.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            confPassword.isNotEmpty()
    }

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun getFocusListener(): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) _viewFocus.value = v
        }
    }
}