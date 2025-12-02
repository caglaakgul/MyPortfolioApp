package com.caglaakgul.myportfolioapp.data.remote

import com.caglaakgul.myportfolioapp.data.remote.dto.ProjectDto

class FakeProjectApi : ProjectApi {

    override suspend fun getProjects(): List<ProjectDto> {
        return listOf(
            ProjectDto(
                id = "cocktailist",
                name = "Cocktailist",
                description = "Cocktail recipe app filters.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Room", "Firebase"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.cocktailist",
                githubUrl = null
            ),
            ProjectDto(
                id = "quickbite",
                name = "QuickBite",
                description = "Food recipe app.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Coroutines", "Firebase"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.quickbite",
                githubUrl = null
            )
        )
    }
}