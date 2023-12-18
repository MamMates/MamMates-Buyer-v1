package com.mammates.mammates_buyer_v1.presentation.util.navigation

sealed class NavigationRoutes(val route: String) {

    data object Introduction : NavigationRoutes("introduction")

    data object Auth : NavigationRoutes("auth") {
        data object Login : NavigationRoutes("login")
        data object Register : NavigationRoutes("register")
        data object ResetPassword : NavigationRoutes("reset_password")
    }

    data object Main : NavigationRoutes("main") {
        data object Home : NavigationRoutes("home")
        data object Search : NavigationRoutes("search")
        data object Order : NavigationRoutes("order")
        data object OrderConfirm : NavigationRoutes("order_confirm")
        data object Account : NavigationRoutes("account")
        data object Store : NavigationRoutes("store")
        data object AccountSetting : NavigationRoutes("account_setting")
        data object ChangePassword : NavigationRoutes("change_password")
    }
}