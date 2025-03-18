package com.example.e_commerceapp.auth.presentation.register

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


@Composable
fun RegisterScreen(
    state: RegisterState,
    modifier: Modifier = Modifier,
    onAction: (RegisterActions) -> Unit,
    navigateToLoginScreen: () -> Unit = {},
    navigateToActivation : () -> Unit = {}
) {

    Box(
        modifier = modifier.fillMaxSize().padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    NormalTextComponent(value = stringResource(id = R.string.hello))
                    HeadingTextComponent(value = stringResource(id = R.string.create_account))
                    Spacer(modifier = Modifier.height(20.dp))

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        icon = Icons.Filled.Person,
                        onTextChanged = {
                            onAction(
                                RegisterActions.FirstNameChanged(it)
                            )
                        },
                        errorStatus = false
                    )

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        icon = Icons.Filled.Person,
                        onTextChanged = {
                            onAction(
                                RegisterActions.LastNameChanged(it)
                            )
                        },
                        errorStatus = false
                    )

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.email),
                        icon = Icons.Filled.Email,
                        onTextChanged = {
                            onAction(
                                RegisterActions.EmailChanged(it)
                            )
                        },
                        errorStatus = false
                    )

                    PasswordTextFieldComponent(
                        labelValue = stringResource(id = R.string.password),
                        icon = Icons.Filled.Lock,
                        onTextSelected = {
                            onAction(
                                RegisterActions.PasswordChanged(it)
                            )
                        },
                        errorStatus = false
                    )

//                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
//                    onTextSelected = {
//                        PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
//                    },
//                    onCheckedChange = {
//                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
//                    }
//                )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        value = stringResource(id = R.string.register),
                        onButtonClicked = {
                            onAction(RegisterActions.OnRegister)
                        },
                        isEnabled = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DividerTextComponent()

                    ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                        navigateToLoginScreen()
                    })
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            modifier = Modifier.clickable {
                                navigateToActivation()
                            },
                            text = "Activate Account",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }

                }

            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }

        }


    }


}

internal val user = RegisterState(
    data = RegisterDetails(
        firstName = "Saurav",
        lastName = "Gupta",
        email = "sa@gmail.com",
        password = "jnkv"
    )
)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    RegisterScreen(
        modifier = Modifier.padding(16.dp),
        state = user,
        onAction = {}
    )
}