package com.mammates.mammates_buyer_v1.presentation.pages.auth.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.R
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingAnimation
import com.mammates.mammates_buyer_v1.presentation.component.text_field.PasswordTextField
import com.mammates.mammates_buyer_v1.presentation.component.text_field.PrimaryTextField
import com.mammates.mammates_buyer_v1.presentation.pages.auth.login.component.LoginTitle
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes


@Composable
fun LoginScreen(
    navController: NavController,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = state.isAuth) {
        if (state.isAuth) {
            navController.navigate(NavigationRoutes.Main.route) {
                popUpTo(NavigationRoutes.Auth.route) {
                    inclusive = true
                }
            }
        }
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        AlertDialog(
            title = {
                Text(text = "Login Failed")
            },
            text = {
                Text(
                    text = state.errorMessage,
                    textAlign = TextAlign.Center
                )

            },
            onDismissRequest = {
                state.errorMessage.isEmpty()
            },
            icon = {
                Icon(Icons.Default.Info, contentDescription = "Alert Dialog")
            },
            confirmButton = {
                TextButton(onClick = {
                    onEvent(LoginEvent.OnDismissErrorDialog)
                }) {
                    Text(text = "Okay")

                }
            }
        )
    }


    if (state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingAnimation()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = "Login illustration",
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginTitle(
                title = "Welcome Back !",
                description = "Empower the journey towards a sustainable environment"
            )
            Spacer(modifier = Modifier.height(30.dp))
            PrimaryTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.OnChangeEmail(it)) },
                errorResult = state.emailValidationResult,
                label = "Email"
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                onValueChange = {
                    onEvent(LoginEvent.OnChangePassword(it))
                },
                label = "Password",
                value = state.password,
                errorResult = state.passwordValidationResult,
                isPasswordVisible = state.isPasswordVisible,
                togglePasswordVisible = {
                    onEvent(LoginEvent.TogglePasswordVisibility)
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        navController.navigate(NavigationRoutes.Auth.ResetPassword.route)
                    },
                ) {
                    Text(
                        text = "Forgot password ?",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }


            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(LoginEvent.OnLogin)
                }
            ) {
                Text(
                    text = "Login"
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Don’t have an account ?",
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        navController.navigate(NavigationRoutes.Auth.Register.route)
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
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        state = LoginState(
            email = "",
            password = "",
        ),
        onEvent = {}
    )
}