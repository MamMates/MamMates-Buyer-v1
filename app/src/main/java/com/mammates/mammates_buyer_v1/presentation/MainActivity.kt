package com.mammates.mammates_buyer_v1.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.presentation.component.bottom_navigation.BottomNavigation
import com.mammates.mammates_buyer_v1.presentation.component.bottom_navigation.bottomNavigationItem
import com.mammates.mammates_buyer_v1.presentation.component.top_navigation.TopBackNavigation
import com.mammates.mammates_buyer_v1.presentation.component.top_navigation.TopNavigation
import com.mammates.mammates_buyer_v1.presentation.ui.theme.MamMatesBuyerv1Theme
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationGraph
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()

            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            MamMatesBuyerv1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    Scaffold(
                        bottomBar = {
                            if (bottomNavigationItem.firstOrNull { it.route == currentRoute } != null) {
                                BottomNavigation(
                                    navController = navController,
                                    items = bottomNavigationItem,
                                )
                            }
                        },

                        topBar = {
                            if (bottomNavigationItem.firstOrNull { it.route == currentRoute } != null) {
                                TopNavigation()
                            } else if (
                                currentRoute != NavigationRoutes.Auth.Login.route &&
                                currentRoute != NavigationRoutes.Introduction.route
                            ) {
                                TopBackNavigation(navController = navController)
                            }
                        }
                    ) { innerPadding ->
                        NavigationGraph(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,

                            )
                    }
                }
            }
        }
    }
}
