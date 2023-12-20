package com.mammates.mammates_buyer_v1.presentation.pages.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.mammates.mammates_buyer_v1.domain.model.featuredStore
import com.mammates.mammates_buyer_v1.presentation.component.dialog.ErrorDialog
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingScreen
import com.mammates.mammates_buyer_v1.presentation.component.text_field.SearchTextField
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.component.CardArticle
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.component.CardFood
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.component.CardStore
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.component.NoRecommendationLabel
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.component.cardArticleItems
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import com.mammates.mammates_buyer_v1.util.HttpError

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, state: HomeState, onEvent: (HomeEvent) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(refreshing = state.isLoading, onRefresh = {
        onEvent(HomeEvent.OnRefreshPage)
    })
    val scrollState = rememberScrollState()


    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.STARTED -> {
                onEvent(HomeEvent.OnRefreshPage)
            }

            else -> {}
        }
    }

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
    if (state.isNotAuthorizeDialogOpen) {
        ErrorDialog(
            message = HttpError.UNAUTHORIZED.message,
            onConfirm = {
                onEvent(HomeEvent.ClearToken)
            },
            title = "Unauthorized User !"
        )
    }

    if (state.isLoading) {
        LoadingScreen(
            Modifier.fillMaxSize()
        )
    } else {
        Box(
            modifier = Modifier.pullRefresh(pullRefreshState),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 35.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "What would you like to order?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(25.dp))
                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .clickable {
                            navController.navigate(NavigationRoutes.Main.Search.route)
                        },
                    value = state.searchText,
                    placeholder = "Find the food you want",
                    onValueChange = {
                        onEvent(HomeEvent.OnChangeSearchText(it))
                    },
                    isEnable = false
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Popular Food",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (state.foodsRecommendation.isEmpty()) {
                    NoRecommendationLabel()
                } else {
                    LazyRow {
                        items(state.foodsRecommendation) { item ->
                            CardFood(
                                modifier = Modifier.clickable {
                                    navController.navigate(NavigationRoutes.Main.Store.route + "?store_id=${item.storeId}")
                                },
                                name = item.name,
                                price = item.price,
                                image = item.image
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Popular Food",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(featuredStore, key = { it.id }) { item ->
                        CardStore(
                            name = item.name,
                            address = item.address,
                            image = item.image,
                            onCLick = {
                                navController.navigate(NavigationRoutes.Main.ComingSoon.route)
                            }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Article",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
                cardArticleItems.forEach { items ->
                    CardArticle(
                        title = items.title,
                        illustration = items.illustration,
                        description = items.description,
                        url = items.url
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            PullRefreshIndicator(refreshing = state.isLoading, state = pullRefreshState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeState(
//            isActiveSearch = true
        ),
        onEvent = {}
    )
}