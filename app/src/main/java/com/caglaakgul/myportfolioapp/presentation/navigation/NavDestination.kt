package com.caglaakgul.myportfolioapp.presentation.navigation

sealed class NavDestination(val route: String) {
    data object Home : NavDestination("home")
    data object ProjectDetail : NavDestination("project_detail/{projectId}") {
        fun createRoute(projectId: String) = "project_detail/$projectId"
    }
    data object About : NavDestination("about")
}