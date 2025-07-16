package com.example.dancestudio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            DanceStudioWelcomeScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
    }
}
