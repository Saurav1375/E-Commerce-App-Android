package com.example.e_commerceapp.auth.presentation.register

import com.example.e_commerceapp.core.domain.util.NetworkError


sealed interface RegisterEvents {
    data class Error(val error: NetworkError): RegisterEvents
    data object Success: RegisterEvents
}