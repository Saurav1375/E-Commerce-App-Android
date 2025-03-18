package com.example.e_commerceapp.auth.presentation.activate_account


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.example.e_commerceapp.auth.presentation.activate_account.components.OtpInputField
import com.example.e_commerceapp.auth.presentation.components.HeadingTextComponent
import com.example.e_commerceapp.auth.presentation.components.NormalTextComponent

@Composable
fun ActivationScreen(
    state: ActivationState,
    focusRequesters: List<FocusRequester>,
    onAction: (ActivationActions) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeadingTextComponent(
            "Activate Account"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NormalTextComponent(
            "Enter the activation code sent to your email"
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            state.code.forEachIndexed { index, number ->
                OtpInputField(
                    number = number,
                    focusRequester = focusRequesters[index],
                    onFocusChanged = { isFocused ->
                        if(isFocused) {
                            onAction(ActivationActions.OnChangeFieldFocused(index))
                        }
                    },
                    onNumberChanged = { newNumber ->
                        onAction(ActivationActions.OnEnterNumber(newNumber, index))
                    },
                    onKeyboardBack = {
                        onAction(ActivationActions.OnKeyboardBack)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        }

    }
}