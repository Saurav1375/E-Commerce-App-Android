package com.example.e_commerceapp.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.auth.data.local.TokenManager
import com.example.e_commerceapp.auth.domain.AuthService
import com.example.e_commerceapp.auth.domain.TokenResponse
import com.example.e_commerceapp.core.domain.util.onError
import com.example.e_commerceapp.core.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : ViewModel(){
    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: LoginActions) {
        when (action) {
            is LoginActions.EmailChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            email = action.email
                        )
                    )
                }
            }

            is LoginActions.PasswordChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            password = action.password
                        )
                    )
                }
            }

            LoginActions.OnLogin -> {
                login(details = state.value.data)


            }
        }

    }
    private fun login(
        details: LoginDetails
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            authService.login(
                email = details.email,
                password = details.password
            )
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            tokenResponse = it.tokenResponse
                        )
                    }
                    tokenManager.saveTokens(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken
                    )
                    _events.send(LoginEvents.Success)
                }.onError {
                    _state.update { it.copy(isLoading = false) }
                    _events.send(LoginEvents.Error(it))
                }

        }

    }

    suspend fun getCurrentlyLoggedInUser(): TokenResponse? {
        return authService.getCurrentlyLoggedInUser()
    }
    suspend fun logout() {
        tokenManager.clearTokens()
    }

}