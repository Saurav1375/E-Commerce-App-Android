package com.example.e_commerceapp.ecommerce.presentation.home_screen

import com.example.e_commerceapp.core.domain.util.NetworkError

sealed interface ProductEvent {
    data class Error(val error: NetworkError): ProductEvent
}