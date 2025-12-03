package com.caglaakgul.myportfolioapp.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caglaakgul.myportfolioapp.presentation.home.HomeScreen
import com.caglaakgul.myportfolioapp.presentation.intro.IntroScreen

@Composable
fun MyPortfolioNavHost(
    navController: NavHostController
) {
        NavHost(
            navController = navController,
            startDestination = NavDestination.Intro.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(NavDestination.Intro.route) {
                IntroScreen(
                    onFinished = {
                        navController.navigate(NavDestination.Home.route) {
                            popUpTo(NavDestination.Intro.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(NavDestination.Home.route) {
                HomeScreen(onProjectClick = { id ->
                    navController.navigate(NavDestination.ProjectDetail.createRoute(id))
                }, onAboutClick = {
                    navController.navigate(NavDestination.About.route)
                })
            }

            composable(NavDestination.About.route) {}

            composable(NavDestination.ProjectDetail.route) {}
        }
}
