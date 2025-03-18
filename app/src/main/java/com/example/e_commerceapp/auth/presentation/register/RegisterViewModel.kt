package com.example.e_commerceapp.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.auth.domain.AuthService
import com.example.e_commerceapp.core.domain.util.onError
import com.example.e_commerceapp.core.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel (
    private val authService: AuthService
) : ViewModel(){
    private val _state = MutableStateFlow<RegisterState>(RegisterState())
    val state = _state.asStateFlow()

    private val _events = Channel<RegisterEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: RegisterActions) {
        when (action) {
            is RegisterActions.FirstNameChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            firstName = action.firstName
                        )
                    )
                }
            }

            is RegisterActions.LastNameChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            lastName = action.lastName
                        )
                    )
                }
            }

            is RegisterActions.EmailChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            email = action.email
                        )
                    )
                }
            }

            is RegisterActions.PasswordChanged -> {
                _state.update {
                    it.copy(
                        data = it.data.copy(
                            password = action.password
                        )
                    )
                }
            }

            RegisterActions.OnRegister -> {
                register(
                    details = state.value.data
                )


            }
        }

    }
    private fun register(
        details: RegisterDetails
    ) {
//        println("Details : $details")
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            authService.register(details.toAuthRequest())
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _events.send(RegisterEvents.Success)
                }.onError {
                    _state.update { it.copy(isLoading = false) }
                    _events.send(RegisterEvents.Error(it))
                }

        }

    }

}