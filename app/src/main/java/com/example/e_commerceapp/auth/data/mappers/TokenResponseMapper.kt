package com.example.e_commerceapp.auth.data.mappers

import com.example.e_commerceapp.auth.data.networking.dto.TokenResponseDto
import com.example.e_commerceapp.auth.domain.TokenResponse

fun TokenResponseDto.toTokenResponse() : TokenResponse {
    return TokenResponse(
        accessToken = access_token,
        refreshToken = refresh_token
    )
}