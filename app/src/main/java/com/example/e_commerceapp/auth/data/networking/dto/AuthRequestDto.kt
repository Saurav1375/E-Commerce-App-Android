package com.example.e_commerceapp.auth.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto (
    val firstname : String,
    val lastname : String,
    val email : String,
    val password : String,
)