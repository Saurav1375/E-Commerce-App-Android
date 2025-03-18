package com.example.e_commerceapp.auth.presentation.login

sealed interface LoginActions {
    data object OnLogin : LoginActions
    data class EmailChanged(val email: String) : LoginActions
    data class PasswordChanged(val password: String) : LoginActions

}