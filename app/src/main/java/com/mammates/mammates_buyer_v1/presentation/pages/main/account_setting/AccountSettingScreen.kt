package com.mammates.mammates_buyer_v1.presentation.pages.main.account_setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ConfirmDialog
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ErrorDialog
import com.mammates.mammates_buyer_v1.presentation.component.dialog.SuccessDialog
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingAnimation
import com.mammates.mammates_buyer_v1.presentation.component.text_field.FormImageTextField
import com.mammates.mammates_buyer_v1.presentation.component.text_field.FormTextField
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import com.mammates.mammates_buyer_v1.util.HttpError

@Composable
fun AccountSettingScreen(
    navController: NavController,
    state: AccountSettingState,
    onEvent: (AccountSettingEvent) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()


    if (state.isNotAuthorizeDialogOpen) {
        ErrorDialog(
            message = HttpError.UNAUTHORIZED.message,
            onConfirm = {
                onEvent(AccountSettingEvent.ClearToken)
            }
        )
    }

    if (!state.successMessage.isNullOrEmpty()) {
        SuccessDialog(
            message = state.successMessage + ", please login again!",
            onConfirm = {
                onEvent(AccountSettingEvent.OnDismissDialog)
                onEvent(AccountSettingEvent.ClearToken)
            }
        )
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(AccountSettingEvent.OnDismissDialog)
            }
        )
    }

    if (state.isConfirmDialogOpen) {
        ConfirmDialog(
            message = "Are you sure wanna save this changes ?",
            onConfirm = {
                onEvent(AccountSettingEvent.OnConfirmChangesAccount(context))
            },
            onDismiss = {
                onEvent(AccountSettingEvent.OnDismissDialog)
            }
        )
    }

    LaunchedEffect(key1 = state.token) {
        if (state.token.isEmpty()) {
            navController.navigate(route = NavigationRoutes.Auth.route) {
                popUpTo(route = NavigationRoutes.Main.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = state.token) {
        if (state.token.isEmpty()) {
            navController.navigate(route = NavigationRoutes.Auth.route) {
                popUpTo(route = NavigationRoutes.Main.route) {
                    inclusive = true
                }
            }
        }
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Account Setting",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormImageTextField(
                label = "Profile Picture",
                description = "Give your profile a personal touch by uploading a profile photo that reflects your uniqueness and personality here.",
                onImageCapture = {
                    onEvent(AccountSettingEvent.OnChangeProfileImage(it))
                },
                imageUri = state.profileImageUri,
                imageUrl = state.profileImageUrl
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormTextField(
                value = state.fullName,
                onValueChange = {
                    onEvent(AccountSettingEvent.OnChangeFullName(it))
                },
                errorResult = state.fullNameValidation,
                label = "Full Name",
                description = "Keep your identity fresh – update your full name to reflect any changes or new beginnings in your culinary journey."
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormTextField(
                value = state.email,
                onValueChange = {
                    onEvent(AccountSettingEvent.OnChangeEmail(it))

                },
                errorResult = state.emailValidation,
                label = "Email",
                description = "Stay in the loop – modify your email to receive the latest updates and opportunities from MamMates."
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(AccountSettingEvent.OnOpenConfirmDialog)
                }) {
                Text(text = "Save Update")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}