package com.example.e_commerceapp.auth.domain

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)