package com.example.e_commerceapp.auth.presentation.register

data class RegisterState(
    val data: RegisterDetails = RegisterDetails(),
    val isLoading: Boolean = false,
)