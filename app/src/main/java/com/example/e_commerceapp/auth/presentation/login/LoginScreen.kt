package com.example.e_commerceapp.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerceapp.R
import com.example.e_commerceapp.auth.presentation.components.ButtonComponent
import com.example.e_commerceapp.auth.presentation.components.ClickableLoginTextComponent
import com.example.e_commerceapp.auth.presentation.components.DividerTextComponent
import com.example.e_commerceapp.auth.presentation.components.HeadingTextComponent
import com.example.e_commerceapp.auth.presentation.components.MyTextFieldComponent
import com.example.e_commerceapp.auth.presentation.components.NormalTextComponent
import com.example.e_commerceapp.auth.presentation.components.PasswordTextFieldComponent
import com.example.e_commerceapp.auth.presentation.components.UnderLinedTextComponent


@Composable
fun LoginScreen(
    state: LoginState,
    modifier: Modifier = Modifier,
    onAction: (LoginActions) -> Unit,
    navigateToRegisterScreen: () -> Unit = {},
    navigateToActivation : () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    icon = Icons.Filled.Email,
                    onTextChanged = {
                        onAction(LoginActions.EmailChanged(it))
                    },
                    errorStatus = false
                )
                Spacer(modifier = Modifier.height(4.dp))
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    icon = Icons.Filled.Lock,
                    onTextSelected = {
                        onAction(LoginActions.PasswordChanged(it))
                    },
                    errorStatus = false
                )

                Spacer(modifier = Modifier.height(40.dp))
//                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        onAction(LoginActions.OnLogin)
                    },
                    isEnabled = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navigateToRegisterScreen()
                })
            }
        }

        if(state.isLoading) {
            CircularProgressIndicator()
        }
    }


}

internal val user = LoginState(
    data = LoginDetails(
        email = "sa@gmail.com",
        password = "jnkv"
    )
)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    LoginScreen(
        modifier = Modifier.padding(16.dp),
        state = user,
        onAction = {}
    )
}