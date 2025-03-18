package com.example.e_commerceapp.auth.domain

import com.example.e_commerceapp.core.domain.util.NetworkError
import com.example.e_commerceapp.core.domain.util.Result

interface AuthService {
    suspend fun register(request: AuthRequest): Result<Unit, NetworkError>
    suspend fun login(email: String, password: String): Result<TokenResponse, NetworkError>
    suspend fun refreshToken(refreshToken: String): Result<TokenResponse, NetworkError>
    suspend fun activateAccount(token: String): Result<Unit, NetworkError>
    suspend fun getCurrentlyLoggedInUser(): TokenResponse?

}