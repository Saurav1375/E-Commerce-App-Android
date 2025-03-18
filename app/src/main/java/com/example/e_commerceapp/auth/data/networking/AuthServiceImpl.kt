package com.example.e_commerceapp.auth.data.networking

import com.example.e_commerceapp.auth.data.local.TokenManager
import com.example.e_commerceapp.auth.data.mappers.toAuthRequestDto
import com.example.e_commerceapp.auth.data.mappers.toTokenResponse
import com.example.e_commerceapp.auth.data.networking.dto.AuthRequestDto
import com.example.e_commerceapp.auth.data.networking.dto.TokenResponseDto
import com.example.e_commerceapp.auth.domain.AuthRequest
import com.example.e_commerceapp.auth.domain.AuthService
import com.example.e_commerceapp.auth.domain.TokenResponse
import com.example.e_commerceapp.core.domain.util.NetworkError
import com.example.e_commerceapp.core.data.networking.constructUrl
import com.example.e_commerceapp.core.data.networking.safeCall
import com.example.e_commerceapp.core.domain.util.Result
import com.example.e_commerceapp.core.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthServiceImpl(
    private val httpClient: HttpClient,
    private val tokenManager: TokenManager
) : AuthService{
    override suspend fun register(request: AuthRequest): Result<Unit, NetworkError> {
        return safeCall<Unit> {
            httpClient.post(constructUrl("/auth/register")) {
                contentType(ContentType.Application.Json)
                setBody(request.toAuthRequestDto())
            }
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<TokenResponse, NetworkError> {
        return safeCall<TokenResponseDto> {
            httpClient.post(constructUrl("/auth/authenticate")) {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "email" to email,
                        "password" to password,
                    )
                )
            }
        }.map(TokenResponseDto::toTokenResponse)
    }

    override suspend fun refreshToken(refreshToken: String): Result<TokenResponse, NetworkError> {
        return safeCall<TokenResponseDto> {
            httpClient.post(constructUrl("/auth/refresh-token")) {
                contentType(ContentType.Application.Json)
            }
        }.map(TokenResponseDto::toTokenResponse)
    }

    override suspend fun activateAccount(token: String): Result<Unit, NetworkError> {
        return safeCall<Unit> {
            httpClient.get(constructUrl("/auth/activate-account")) {
                contentType(ContentType.Application.Json)
                url {
                    parameters.append("token", token)
                }
            }
        }
    }

    override suspend fun getCurrentlyLoggedInUser(): TokenResponse? {
        val accessToken = tokenManager.getAccessToken()
        val refreshToken = tokenManager.getRefreshToken()
        if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
            return null
        }
        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )

    }
}