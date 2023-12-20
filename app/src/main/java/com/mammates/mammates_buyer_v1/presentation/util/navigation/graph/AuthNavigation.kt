package com.mammates.mammates_buyer_v1.presentation.util.navigation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mammates.mammates_buyer_v1.presentation.pages.auth.forgot_passsword.ResetPasswordScreen
import com.mammates.mammates_buyer_v1.presentation.pages.auth.forgot_passsword.ResetPasswordViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.auth.login.LoginScreen
import com.mammates.mammates_buyer_v1.presentation.pages.auth.login.LoginViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.auth.register.RegisterScreen
import com.mammates.mammates_buyer_v1.presentation.pages.auth.register.RegisterViewModel
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Auth.route,
        startDestination = NavigationRoutes.Auth.Login.route
    ) {
        composable(route = NavigationRoutes.Auth.Login.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsState()

            LoginScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Auth.Register.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val state by viewModel.state.collectAsState()
            RegisterScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )

        }
        composable(route = NavigationRoutes.Auth.ResetPassword.route) {
            val viewModel = hiltViewModel<ResetPasswordViewModel>()
            val state by viewModel.state.collectAsState()
            ResetPasswordScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}