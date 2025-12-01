package com.caglaakgul.myportfolioapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyPortfolioNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route
    ) {
        composable(NavDestination.Home.route) {
        }

        composable(NavDestination.About.route) {
        }

        composable(NavDestination.ProjectDetail.route) {}
    }
}
