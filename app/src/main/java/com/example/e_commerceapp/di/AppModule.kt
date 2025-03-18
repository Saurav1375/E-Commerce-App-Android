package com.example.e_commerceapp.di

import com.example.e_commerceapp.auth.data.local.TokenManager
import com.example.e_commerceapp.auth.data.networking.AuthServiceImpl
import com.example.e_commerceapp.auth.domain.AuthService
import com.example.e_commerceapp.auth.presentation.activate_account.ActivationViewModel
import com.example.e_commerceapp.auth.presentation.login.LoginViewModel
import com.example.e_commerceapp.auth.presentation.register.RegisterViewModel
import com.example.e_commerceapp.core.data.networking.HttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(
        engine = CIO.create(),
        tokenManager = get(),

    ) }
    single { TokenManager(get()) }
    singleOf(::AuthServiceImpl).bind<AuthService>()
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ActivationViewModel)
    viewModelOf(::LoginViewModel)
}
