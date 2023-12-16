package com.mammates.mammates_buyer_v1.presentation.pages.main.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AccountScreen(
    navController: NavController,
    state: AccountState,
    onEvent: (AccountEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.exampleState)
    }

}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(
        navController = rememberNavController(),
        state = AccountState(),
        onEvent = {}
    )
}