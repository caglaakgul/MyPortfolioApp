package com.caglaakgul.myportfolioapp.data.remote

import com.caglaakgul.myportfolioapp.data.remote.dto.ProjectDto

class FakeProjectApi : ProjectApi {

    override suspend fun getProjects(): List<ProjectDto> {
        return listOf(
            ProjectDto(
                id = "cocktailist",
                name = "Cocktailist",
                description = "Modern cocktail recipe app with offline support.",
                techStack = listOf("Kotlin", "Compose", "Room", "Hilt", "Firebase"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.cocktailist",
                githubUrl = null,
                category = "personal"
            ),
            ProjectDto(
                id = "quickbite",
                name = "QuickBite",
                description = "Food recipe app with categories and favorites.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Coroutines", "Firebase"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.quickbite",
                githubUrl = null,
                category = "personal"
            ),

            ProjectDto(
                id = "ehliyet",
                name = "Ehliyet Sinav Uygulamasi",
                description = "Driver’s license exam app for Turkish learners.",
                techStack = listOf("Kotlin", "Clean Architecture", "Hilt", "Room", "Coroutines"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.ehliyet",
                githubUrl = null,
                category = "freelance"
            ),

            ProjectDto(
                id = "flight_market",
                name = "Flight Market (SunExpress)",
                description = "Offline in-flight sales app running on Android POS devices.",
                techStack = listOf("Kotlin", "Jetpack Compose", "Room", "Hilt", "WorkManager"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = null,
                category = "professional"
            ),
            ProjectDto(
                id = "rem_instore",
                name = "Rem-inStore",
                description = "Retail execution and auditing app for field teams.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Retrofit", "DataBinding"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.rem.people",
                githubUrl = null,
                category = "professional"
            ),
            ProjectDto(
                id = "ikon_hr",
                name = "IKon HR App",
                description = "Internal HR app for employees at Enerjisa.",
                techStack = listOf("Kotlin", "Coroutines", "Dagger2", "RxJava", "DataBinding"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = null,
                category = "professional"
            )
        )
    }
}