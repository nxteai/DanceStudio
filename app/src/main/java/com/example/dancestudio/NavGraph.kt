package com.example.dancestudio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            DanceStudioWelcomeScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            StudioListScreen(
                navController = navController,
                onStudioSelected = { studio ->
                    navController.navigate("studio/${studio.id}")
                }
            )
        }
        composable(
            route = "studio/{studioId}",
            arguments = listOf(navArgument("studioId") { type = NavType.StringType })
        ) { backStackEntry ->
            val studioId = backStackEntry.arguments?.getString("studioId")!!
            StudioDetailScreen(studioId = studioId, navController = navController)
        }
    }
}
