package com.example.e_commerceapp.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.e_commerceapp.auth.presentation.activate_account.ActivationActions
import com.example.e_commerceapp.auth.presentation.activate_account.ActivationEvents
import com.example.e_commerceapp.auth.presentation.activate_account.ActivationScreen
import com.example.e_commerceapp.auth.presentation.activate_account.ActivationViewModel
import com.example.e_commerceapp.auth.presentation.login.LoginEvents
import com.example.e_commerceapp.auth.presentation.login.LoginScreen
import com.example.e_commerceapp.auth.presentation.login.LoginViewModel
import com.example.e_commerceapp.auth.presentation.register.RegisterEvents
import com.example.e_commerceapp.auth.presentation.register.RegisterScreen
import com.example.e_commerceapp.auth.presentation.register.RegisterViewModel
import com.example.e_commerceapp.core.presentation.util.ObserveAsEvents
import com.example.e_commerceapp.core.presentation.util.toString
import com.example.e_commerceapp.ecommerce.presentation.home_screen.HomeScreen
import com.example.e_commerceapp.ecommerce.presentation.home_screen.ProductAction
import com.example.e_commerceapp.ecommerce.presentation.home_screen.ProductEvent
import com.example.e_commerceapp.ecommerce.presentation.home_screen.ProductsViewModel
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.ProductDetailScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val loginViewModel = koinViewModel<LoginViewModel>()
    val productViewModel = koinViewModel<ProductsViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        modifier = modifier
    ) {
        composable(route = Screen.RegisterScreen.route) {
            val registerViewModel = koinViewModel<RegisterViewModel>()
            val state by registerViewModel.state.collectAsStateWithLifecycle()
            val context = LocalContext.current
            ObserveAsEvents(events = registerViewModel.events) { event ->
                when(event) {
                    is RegisterEvents.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    RegisterEvents.Success -> {
                        Toast.makeText(
                            context,
                            "Registration Successful. A Link has been sent to your email to activate your account.",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(Screen.ActivationScreen.route)
                    }
                }
            }
            RegisterScreen(
                modifier = modifier,
                state = state,
                onAction = {
                    registerViewModel.onAction(it)

                },
                navigateToLoginScreen = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                navigateToActivation = {
                    navController.navigate(Screen.ActivationScreen.route)
                }
            )
        }
        composable(route = Screen.ActivationScreen.route) {
            val activationViewModel = koinViewModel<ActivationViewModel>()
            val state by activationViewModel.state.collectAsStateWithLifecycle()

            val context = LocalContext.current
            ObserveAsEvents(events = activationViewModel.events) { event ->
                when(event) {
                    is ActivationEvents.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    ActivationEvents.Success -> {
                        Toast.makeText(
                            context,
                            "Account Activated Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(Screen.LoginScreen.route)
                    }
                }
            }

            val focusRequesters = remember {
                List(6) { FocusRequester() }
            }
            val focusManager = LocalFocusManager.current
            val keyboardManager = LocalSoftwareKeyboardController.current

            LaunchedEffect(state.focusedIndex) {
                state.focusedIndex?.let { index ->
                    focusRequesters.getOrNull(index)?.requestFocus()
                }
            }

            LaunchedEffect(state.code, keyboardManager) {
                val allNumbersEntered = state.code.none { it == null }
                if(allNumbersEntered) {
                    focusRequesters.forEach {
                        it.freeFocus()
                    }
                    focusManager.clearFocus()
                    keyboardManager?.hide()
                }
            }

            ActivationScreen(
                state = state,
                focusRequesters = focusRequesters,
                onAction = { action ->
                    when(action) {
                        is ActivationActions.OnEnterNumber -> {
                            if(action.number != null) {
                                focusRequesters[action.index].freeFocus()
                            }
                        }
                        else -> Unit
                    }
                    activationViewModel.onAction(action)
                },
                modifier = modifier
            )
        }
        composable(route = Screen.LoginScreen.route) {

            LaunchedEffect(Unit) {
                if(loginViewModel.getCurrentlyLoggedInUser() != null) {
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
            val state by loginViewModel.state.collectAsStateWithLifecycle()
            val context = LocalContext.current
            ObserveAsEvents(events = loginViewModel.events) { event ->
                when(event) {
                    is LoginEvents.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    LoginEvents.Success -> {
                        Toast.makeText(
                            context,
                            "Login Successful",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(Screen.HomeScreen.route)
                    }
                }
            }
            LoginScreen(
                modifier = modifier,
                state = state,
                onAction = loginViewModel::onAction,
                navigateToRegisterScreen = {
                    navController.navigate(Screen.RegisterScreen.route)
                },
                navigateToActivation = {
                    navController.navigate(Screen.ActivationScreen.route)
                }
            )
        }

        composable(route = Screen.HomeScreen.route) {
            val context =  LocalContext.current
            val scope = rememberCoroutineScope()
            val state by productViewModel.state.collectAsStateWithLifecycle()

            ObserveAsEvents(events = productViewModel.events) { event ->
                when(event) {
                    is ProductEvent.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            HomeScreen(
                state = state,
                onAction = { action ->
                    productViewModel.onAction(action)
                    when(action) {
                        is ProductAction.OnProductSelected -> {
                            navController.navigate(
                                Screen.ProductDetailScreen.route
                            )
                        }
                        else -> Unit
                    }
                },
                modifier = modifier
            )
        }

        composable(route = Screen.ProductDetailScreen.route) {
            val context =  LocalContext.current
            val scope = rememberCoroutineScope()
            val state by productViewModel.state.collectAsStateWithLifecycle()
            ObserveAsEvents(events = productViewModel.events) { event ->
                when(event) {
                    is ProductEvent.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            ProductDetailScreen(
                modifier = modifier,
                state = state,
                onAction = productViewModel::onAction,
                onBackPressed = {
                    navController.popBackStack()
                },
            )
        }


    }

}