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
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.doubletech.esinav&utm_source=emea_Med",
                githubUrl = null,
                category = "freelance",
                company = "DoubleTech Studio"
            ),
            ProjectDto(
                id = "github_api_app",
                name = "GitHub API App",
                description = "GitHub user search and repository explorer built with XML + Jetpack Compose hybrid UI and MVVM architecture.",
                techStack = listOf("Kotlin", "MVVM", "Retrofit", "Coroutines", "Glide", "Jetpack Compose"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = "https://github.com/caglaakgul/Android-Github-API-App---XML-and-Jetpack-Compose",
                category = "personal"
            ),
            ProjectDto(
                id = "flight_market",
                name = "Flight Market (SunExpress)",
                description = "Offline in-flight sales app running on Android POS devices.",
                techStack = listOf("Kotlin", "Jetpack Compose", "Room", "Hilt", "WorkManager"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = null,
                category = "professional",
                company = "Avsos-ID3"
            ),
            ProjectDto(
                id = "rem_instore",
                name = "Rem-inStore",
                description = "Retail execution and auditing app for field teams.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Retrofit", "DataBinding"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/search?q=rem%20in%20store&c=apps&utm_source=emea_Med",
                githubUrl = null,
                category = "professional",
                company = "RemPeople"
            ),
            ProjectDto(
                id = "ikon_hr",
                name = "IKon HR App",
                description = "Internal HR app for employees at Enerjisa.",
                techStack = listOf("Kotlin", "Coroutines", "Dagger2", "RxJava", "DataBinding"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.enerjisa.hraas&utm_source=emea_Med",
                githubUrl = null,
                category = "professional",
                company = "EnerjiSa"
            ),
            ProjectDto(
                id = "enuygun",
                name = "ENUYGUN",
                description = "Flight, bus and hotel booking app used by millions of users.",
                techStack = listOf("Kotlin", "Retrofit", "RecyclerView", "Android Jetpack"),
                imageUrl = null,
                playStoreUrl = "https://play.google.com/store/search?q=ENUYGUN&c=apps&utm_source=emea_Med",
                githubUrl = null,
                category = "professional",
                company = "iPucu Bilisim"
            ),

            ProjectDto(
                id = "talknative",
                name = "TalkNative",
                description = "Real-time translation chat app for multilingual texting using Yandex Translate API.",
                techStack = listOf("Kotlin", "MVVM", "Dagger2", "RxJava", "Retrofit", "Glide"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = "https://github.com/caglaakgul/Talk-Native",
                category = "personal"
            ),
            ProjectDto(
                id = "book_donation",
                name = "Book Donation Application",
                description = "Android application for donating or requesting books, built with Java and MySQL backend.",
                techStack = listOf("Java", "MySQL", "Retrofit", "RecyclerView", "Glide"),
                imageUrl = null,
                playStoreUrl = null,
                githubUrl = "https://github.com/caglaakgul/AndroidBookApp",
                category = "personal"
            )
        )
    }
}