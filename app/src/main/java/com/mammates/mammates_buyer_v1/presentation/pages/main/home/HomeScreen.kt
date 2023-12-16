package com.mammates.mammates_buyer_v1.presentation.pages.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    if (!state.isOnBoarding && state.token.isEmpty()) {
        LaunchedEffect(key1 = Unit) {

            navController.navigate(route = NavigationRoutes.Introduction.route) {
                popUpTo(route = NavigationRoutes.Main.route) {
                    inclusive = true
                }
            }
        }
    } else if (state.token.isEmpty()) {
        LaunchedEffect(key1 = Unit) {

            navController.navigate(route = NavigationRoutes.Auth.route) {
                popUpTo(route = NavigationRoutes.Main.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}