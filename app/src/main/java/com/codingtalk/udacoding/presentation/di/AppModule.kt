package com.codingtalk.udacoding.presentation.di

import androidx.preference.PreferenceManager
import com.codingtalk.udacoding.data.helper.SessionManager
import com.codingtalk.udacoding.data.source.login.LoginDataSource
import com.codingtalk.udacoding.data.source.register.RegisterDataSource
import com.codingtalk.udacoding.presentation.view.login.LoginViewModel
import com.codingtalk.udacoding.presentation.view.main.MainViewModel
import com.codingtalk.udacoding.presentation.view.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val prefModule = module {
        single {
            SessionManager(PreferenceManager.getDefaultSharedPreferences(get()))
        }
    }

    val registerModule = module {
        factory { RegisterDataSource(get()) }
        viewModel {
            RegisterViewModel(
                get()
            )
        }
    }

    val loginModule = module {
        factory { LoginDataSource(get()) }
        viewModel {
            LoginViewModel(
                get(),
                get()
            )
        }
    }

    val mainModule = module {
        viewModel {
            MainViewModel(
                get()
            )
        }
    }

    val retrofitModule = module {
        single { Network.provideRetrofitInstance() }
        factory { Network.provideApiService(get()) }
    }
}