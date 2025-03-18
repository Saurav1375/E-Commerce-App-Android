package com.example.e_commerceapp.auth.presentation.register

import com.example.e_commerceapp.auth.domain.AuthRequest

data class RegisterDetails(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)


fun RegisterDetails.toAuthRequest() : AuthRequest {
    return AuthRequest(
        firstname = firstName,
        lastname = lastName,
        email = email,
        password = password
    )
}