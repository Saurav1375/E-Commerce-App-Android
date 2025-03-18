package com.example.e_commerceapp.auth.presentation.activate_account

import com.example.e_commerceapp.auth.presentation.register.RegisterEvents
import com.example.e_commerceapp.core.domain.util.NetworkError

sealed interface ActivationEvents {
    data class Error(val error: NetworkError): ActivationEvents
    data object Success: ActivationEvents
}