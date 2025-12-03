package com.example.jetpackcompoz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompoz.feature.auth.AuthViewModel
import com.example.jetpackcompoz.feature.auth.login.LoginScreen
import com.example.jetpackcompoz.feature.auth.signup.SignUpScreen
import com.example.jetpackcompoz.navigation.Routes
import com.example.jetpackcompoz.navigation.main.BottomNavHost

@Composable
fun App(authViewModel: AuthViewModel = viewModel()) {
    val isLoggedIn = authViewModel.isLoggedIn
    val navController = rememberNavController()

    LaunchedEffect(isLoggedIn) {
        navController.navigate(if (isLoggedIn) Routes.UN_AUTH else Routes.AUTH) {
            popUpTo(0)
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Routes.UN_AUTH else Routes.AUTH
    ) {
        // Unauthorized flow (Login + SignUp)
        navigation(startDestination = Routes.LOGIN, route = Routes.AUTH) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = { authViewModel.updateLoginStatus(true) },
                    onSignUpClick = { navController.navigate(Routes.SIGN_UP) }
                )
            }
            composable(Routes.SIGN_UP) {
                SignUpScreen(onSignUpComplete = {authViewModel.updateLoginStatus(true) })
            }
        }

        // Authorized flow (BottomNav with 4 screens)
        navigation(startDestination = Routes.HOME, route = Routes.UN_AUTH) {
            composable(Routes.HOME) { BottomNavHost(navController) }
        }
    }
}