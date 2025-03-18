package com.example.e_commerceapp.core.data.networking

import android.util.Log
import com.example.e_commerceapp.auth.data.local.TokenManager
import com.example.e_commerceapp.auth.data.mappers.toTokenResponse
import com.example.e_commerceapp.auth.data.networking.dto.TokenResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory{
    fun create(engine: HttpClientEngine, tokenManager: TokenManager): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = tokenManager.getAccessToken()
                        val refreshToken = tokenManager.getRefreshToken()
                        BearerTokens(accessToken ?: "", refreshToken ?: "")
                    }

                    refreshTokens {
                        try {
                            val refreshToken = tokenManager.getRefreshToken()
                            if (!refreshToken.isNullOrEmpty()) {
                                val token = client.post {
                                    markAsRefreshTokenRequest()
                                    url(constructUrl("/auth/refresh-token"))
                                    parameter("token", refreshToken)
                                }.body<TokenResponseDto>()
                                    .toTokenResponse()
                                tokenManager.saveTokens(
                                    accessToken = token.accessToken,
                                    refreshToken = token.refreshToken
                                )
                                BearerTokens(
                                    accessToken = token.accessToken,
                                    refreshToken = token.refreshToken
                                )
                            } else {
                                Log.d("TAG", "refreshTokens: null")
                                //todo() redirect to logout
                                null
                            }

                        } catch (e :Exception) {
                            Log.d("TAG", "refreshTokens: ${e.message}")
                            //todo() redirect to logout
                            null
                        }

                    }
                }

            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }

        }
    }
}