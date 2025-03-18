package com.example.e_commerceapp.auth.data.mappers

import com.example.e_commerceapp.auth.data.networking.dto.AuthRequestDto
import com.example.e_commerceapp.auth.domain.AuthRequest

fun AuthRequest.toAuthRequestDto() : AuthRequestDto {
    return AuthRequestDto(
        firstname = firstname,
        lastname = lastname,
        email = email,
        password = password
    )
}