package com.mammates.mammates_buyer_v1.presentation.pages.main.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.FoodOrderItemDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderItemDto
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ErrorDialog
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.component.CardOrder
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.component.NoOrderLabel
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import com.mammates.mammates_buyer_v1.util.HttpError
import com.mammates.mammates_buyer_v1.util.toOrder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderScreen(
    navController: NavController,
    state: OrderState,
    onEvent: (OrderEvent) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefresh,
        onRefresh = {
            onEvent(OrderEvent.OnRefreshPage)
        }
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.STARTED -> {
                onEvent(OrderEvent.OnRefreshPage)
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
                onEvent(OrderEvent.ClearToken)
            },
            title = "Unauthorized User !"
        )
    }

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(OrderEvent.OnDismissDialog)
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
                .padding(start = 35.dp, end = 35.dp, top = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            if (state.orders.isEmpty()) {
                NoOrderLabel()
            } else {
                LazyColumn {
                    items(state.orders, key = { it.id }) { item ->
                        CardOrder(
                            statusOrder = item.status,
                            store = item.store,
                            total = item.total,
                            foods = item.foods,
                            onSeeDetail = {
                                navController.navigate(NavigationRoutes.Main.OrderDetail.route + "?order_id=${item.id}")
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.isRefresh,
                state = pullRefreshState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(
        navController = rememberNavController(),
        state = OrderState(
            orders = listOf(
                OrderItemDto(
                    id = 1,
                    status = 2,
                    store = "Toko Tude",
                    total = 20000,
                    foods = listOf(
                        FoodOrderItemDto(
                            quantity = 3,
                            price = 5000,
                            name = "Donut Ubi Mawar"
                        ),
                        FoodOrderItemDto(
                            quantity = 1,
                            price = 5000,
                            name = "Donut Ubi Cinta"
                        ),
                    )
                ),
                OrderItemDto(
                    id = 2,
                    status = 2,
                    store = "Toko Tude",
                    total = 20000,
                    foods = listOf(
                        FoodOrderItemDto(
                            quantity = 3,
                            price = 5000,
                            name = "Donut Ubi Mawar"
                        ),
                        FoodOrderItemDto(
                            quantity = 1,
                            price = 5000,
                            name = "Donut Ubi Cinta"
                        ),
                    )
                )
            ).map {
                it.toOrder()
            }
        ),
        onEvent = {}
    )
}