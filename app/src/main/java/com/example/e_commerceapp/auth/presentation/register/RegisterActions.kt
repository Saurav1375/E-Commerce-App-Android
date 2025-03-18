package com.example.e_commerceapp.auth.presentation.register

sealed interface RegisterActions {
    data object OnRegister : RegisterActions
    data class FirstNameChanged(val firstName: String) : RegisterActions
    data class LastNameChanged(val lastName: String) : RegisterActions
    data class EmailChanged(val email: String) : RegisterActions
    data class PasswordChanged(val password: String) : RegisterActions
}