package com.mammates.mammates_buyer_v1.presentation.util.navigation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mammates.mammates_buyer_v1.presentation.pages.main.account.AccountScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.account.AccountViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.HomeScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.HomeViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.SearchScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.SearchViewModel
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Main.route,
        startDestination = NavigationRoutes.Main.Home.route,
    ) {
        composable(route = NavigationRoutes.Main.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Main.Order.route) {
            val viewModel = hiltViewModel<OrderViewModel>()
            val state by viewModel.state.collectAsState()
            OrderScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Main.Account.route) {
            val viewModel = hiltViewModel<AccountViewModel>()
            val state by viewModel.state.collectAsState()
            AccountScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Main.Search.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            val state by viewModel.state.collectAsState()
            SearchScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

    }

}