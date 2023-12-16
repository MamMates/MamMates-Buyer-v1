package com.mammates.mammates_buyer_v1.presentation.component.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)

val bottomNavigationItem = listOf(
    BottomNavigationItem(
        icon = Icons.Outlined.Home,
        label = "Home",
        route = NavigationRoutes.Main.Home.route
    ),
    BottomNavigationItem(
        icon = Icons.Outlined.ShoppingCart,
        label = "Order",
        route = NavigationRoutes.Main.Order.route
    ),
    BottomNavigationItem(
        icon = Icons.Outlined.People,
        label = "Account",
        route = NavigationRoutes.Main.Account.route
    ),
)