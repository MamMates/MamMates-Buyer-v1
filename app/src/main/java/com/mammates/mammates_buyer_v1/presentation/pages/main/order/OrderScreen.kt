package com.mammates.mammates_buyer_v1.presentation.pages.main.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OrderScreen(
    navController: NavController,
    state: OrderState,
    onEvent: (OrderEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        contentAlignment = Alignment.TopCenter
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(
        navController = rememberNavController(),
        state = OrderState(),
        onEvent = {}
    )
}