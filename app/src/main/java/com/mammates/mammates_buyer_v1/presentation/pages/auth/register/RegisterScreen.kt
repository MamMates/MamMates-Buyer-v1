package com.mammates.mammates_buyer_v1.presentation.pages.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.R
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ErrorDialog
import com.mammates.mammates_buyer_v1.presentation.component.dialog.SuccessDialog
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingScreen
import com.mammates.mammates_buyer_v1.presentation.component.text_field.PasswordTextField
import com.mammates.mammates_buyer_v1.presentation.component.text_field.PrimaryTextField
import com.mammates.mammates_buyer_v1.presentation.pages.auth.register.component.RegisterTitle

@Composable
fun RegisterScreen(
    navController: NavController,
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {

    val scrollState = rememberScrollState()

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(RegisterEvent.OnDismissDialogError)
            },
            title = "Register Failed"
        )
    }

    if (!state.successMessage.isNullOrEmpty()) {
        SuccessDialog(
            message = state.successMessage,
            onConfirm = {
                onEvent(RegisterEvent.OnDismissDialogSuccess)
                navController.popBackStack()
            },
            title = "Register Success!"
        )
    }

    if (state.isLoading) {
        LoadingScreen(
            Modifier.fillMaxSize()
        )
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = "Login illustration",
            )
            Spacer(modifier = Modifier.height(30.dp))
            RegisterTitle(
                title = "Be Part As MamMates",
                description = "Come be part of this journey towards the environment we dream of"
            )
            Spacer(modifier = Modifier.height(30.dp))
            PrimaryTextField(
                value = state.name,
                onValueChange = {
                    onEvent(RegisterEvent.OnChangeName(it))
                },
                errorResult = state.nameValidationResult,
                label = "Name"
            )
            Spacer(modifier = Modifier.height(15.dp))
            PrimaryTextField(
                value = state.email,
                onValueChange = {
                    onEvent(RegisterEvent.OnChangeEmail(it))
                },
                errorResult = state.emailValidationResult,
                label = "Email"
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                onValueChange = {
                    onEvent(RegisterEvent.OnChangePassword(it))
                },
                label = "Password",
                value = state.password,
                errorResult = state.passwordValidationResult,
                isPasswordVisible = state.isPasswordVisible,
                togglePasswordVisible = {
                    onEvent(RegisterEvent.TogglePasswordVisibility)
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                onValueChange = {
                    onEvent(RegisterEvent.OnChangeConfirmPassword(it))
                },
                label = "Confirm Password",
                value = state.passwordConfirm,
                errorResult = state.passwordConfirmValidationResult,
                isPasswordVisible = state.isPasswordConfirmVisible,
                togglePasswordVisible = {
                    onEvent(RegisterEvent.TogglePasswordConfirmVisibility)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(RegisterEvent.OnRegister)
                }
            ) {
                Text(
                    text = "Register"
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Already have an account ?",
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navController = rememberNavController(),
        state = RegisterState(),
        onEvent = {}
    )
}