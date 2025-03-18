package com.example.e_commerceapp.auth.presentation.activate_account

data class ActivationState (
    val isLoading : Boolean = false,
    val code: List<Int?> = (1..6).map { null },
    val focusedIndex: Int? = null,
)