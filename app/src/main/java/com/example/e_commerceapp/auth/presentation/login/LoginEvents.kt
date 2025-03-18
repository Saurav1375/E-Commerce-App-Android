package com.example.e_commerceapp.auth.presentation.login

import com.example.e_commerceapp.core.domain.util.NetworkError


sealed interface LoginEvents {
    data class Error(val error: NetworkError): LoginEvents
    data object Success: LoginEvents
}