package com.example.e_commerceapp.auth.domain

data class AuthRequest (
    val firstname : String,
    val lastname : String,
    val email : String,
    val password : String,
)