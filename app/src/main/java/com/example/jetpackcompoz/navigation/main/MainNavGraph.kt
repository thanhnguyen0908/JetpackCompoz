package com.example.jetpackcompoz.navigation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompoz.feature.main.SimpleScreen
import com.example.jetpackcompoz.navigation.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import com.example.jetpackcompoz.feature.main.home.HomeScreen

data class BottomNavItem(val route: String, val label: String, val icon: ImageVector)

private val bottomItems = listOf(
    BottomNavItem(Routes.HOME,   "Home",   Icons.Filled.Home),
    BottomNavItem(Routes.DASHBOARD,   "Dashboard",   Icons.Filled.Build),
    BottomNavItem(Routes.NOTIFICATIONS,"Notifications",Icons.Filled.Notifications),
    BottomNavItem(Routes.SETTINGS,    "Settings",Icons.Filled.Settings)
)

@Composable
fun BottomNavHost(rootNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentDestination?.route
                bottomItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) { HomeScreen() }
            composable(Routes.DASHBOARD) { SimpleScreen("Dashboard Screen") }
            composable(Routes.NOTIFICATIONS) { SimpleScreen("Notifications Screen") }
            composable(Routes.SETTINGS) { SimpleScreen("Settings Screen") }
        }
    }
}
