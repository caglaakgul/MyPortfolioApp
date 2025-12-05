package com.caglaakgul.myportfolioapp.presentation.navigation

sealed class NavDestination(val route: String) {
    data object Intro : NavDestination("intro")
    data object Home : NavDestination("home")
    data object Projects : NavDestination("projects")
    data object About : NavDestination("about")
    data object Experiences : NavDestination("experiences")
    data object Education : NavDestination("education")
    data object TechStack : NavDestination("tech_stack")
}