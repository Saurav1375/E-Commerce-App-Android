package com.example.e_commerceapp.auth.presentation.login

import com.example.e_commerceapp.auth.domain.TokenResponse

data class LoginState(
    val data: LoginDetails = LoginDetails(),
    val tokenResponse: TokenResponse? = null,
    val isLoading: Boolean = false,
)