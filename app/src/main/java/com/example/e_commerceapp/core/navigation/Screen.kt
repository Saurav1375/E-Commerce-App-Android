package com.example.e_commerceapp.core.navigation

sealed class Screen(
    val route: String
) {
    data object RegisterScreen : Screen("register_screen")
    data object LoginScreen: Screen("login_screen")
    data object  ActivationScreen: Screen("activation_screen")
    data object HomeScreen: Screen("home_screen")

}