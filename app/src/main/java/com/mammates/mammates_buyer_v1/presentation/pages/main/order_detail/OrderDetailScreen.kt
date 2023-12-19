package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ErrorDialog
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingScreen
import com.mammates.mammates_buyer_v1.presentation.component.text.TextLabelValue
import com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail.component.CardOrderFood
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import com.mammates.mammates_buyer_v1.util.HttpError

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderDetailScreen(
    navController: NavController,
    state: OrderDetailState,
    onEvent: (OrderDetailEvent) -> Unit
) {

    val dummyList = (1..3).toList()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefresh,
        onRefresh = {
            onEvent(OrderDetailEvent.OnRefreshPage)
        }
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.STARTED -> {
                onEvent(OrderDetailEvent.OnRefreshPage)
            }

            else -> {}
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

    if (state.isNotAuthorizeDialogOpen) {
        ErrorDialog(
            message = HttpError.UNAUTHORIZED.message,
            onConfirm = {
                onEvent(OrderDetailEvent.ClearToken)
            },
            title = "Unauthorized User !"
        )
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(OrderDetailEvent.OnDismissDialog)
            }
        )
    }



    if (state.isLoading) {
        LoadingScreen(
            Modifier.fillMaxSize()
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(horizontal = 35.dp),
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "See Your Order",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                    TextLabelValue(
                        label = "Invoice",
                        value = state.invoice
                    )
                    TextLabelValue(
                        label = "Store",
                        value = state.storeName
                    )
                    TextLabelValue(
                        label = "Date",
                        value = state.date
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(state.foods) { item ->
                    CardOrderFood(
                        foodName = item.name,
                        quantity = item.quantity,
                        image = item.image,
                        price = item.price
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    TextLabelValue(
                        label = "Total",
                        value = "Rp. ${state.total}"
                    )
                    TextLabelValue(
                        label = "Payment Method",
                        value = "Cash On Delivery"
                    )
                    TextLabelValue(
                        label = "Status",
                        statusOrder = state.status
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview() {
    OrderDetailScreen(
        navController = rememberNavController(),
        state = OrderDetailState(),
        onEvent = {}
    )
}