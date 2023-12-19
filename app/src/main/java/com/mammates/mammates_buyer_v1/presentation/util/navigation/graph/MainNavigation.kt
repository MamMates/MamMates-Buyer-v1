package com.mammates.mammates_buyer_v1.presentation.util.navigation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mammates.mammates_buyer_v1.presentation.pages.main.account.AccountScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.account.AccountViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.account_setting.AccountSettingScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.account_setting.AccountSettingViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.change_password.ChangePasswordScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.change_password.ChangePasswordViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.HomeScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.home.HomeViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.order.OrderViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail.OrderDetailScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail.OrderDetailViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.SearchScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.SearchViewModel
import com.mammates.mammates_buyer_v1.presentation.pages.main.store.StoreScreen
import com.mammates.mammates_buyer_v1.presentation.pages.main.store.StoreViewModel
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
            val foods by viewModel.foods.collectAsState()
            val keywords by viewModel.keywords.collectAsState()
            SearchScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent,
                foods = foods,
                keywords = keywords
            )
        }
        composable(
            route = NavigationRoutes.Main.Store.route + "?store_id={store_id}",
            arguments = listOf(
                navArgument(
                    name = "store_id",
                ) {
                    type = NavType.IntType
                    defaultValue = -69
                }
            )
        ) {
            val viewModel = hiltViewModel<StoreViewModel>()
            val state by viewModel.state.collectAsState()
            StoreScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            route = NavigationRoutes.Main.OrderDetail.route + "?order_id={order_id}",
            arguments = listOf(
                navArgument(
                    name = "order_id",
                ) {
                    type = NavType.IntType
                    defaultValue = -69
                }
            )
        ) {
            val viewModel = hiltViewModel<OrderDetailViewModel>()
            val state by viewModel.state.collectAsState()
            OrderDetailScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Main.AccountSetting.route) {
            val viewModel = hiltViewModel<AccountSettingViewModel>()
            val state by viewModel.state.collectAsState()
            AccountSettingScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = NavigationRoutes.Main.ChangePassword.route) {
            val viewModel = hiltViewModel<ChangePasswordViewModel>()
            val state by viewModel.state.collectAsState()
            ChangePasswordScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }

    }

}