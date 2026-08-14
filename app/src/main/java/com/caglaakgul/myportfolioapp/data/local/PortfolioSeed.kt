package com.caglaakgul.myportfolioapp.data.local

import com.caglaakgul.myportfolioapp.data.local.entity.AboutLocal
import com.caglaakgul.myportfolioapp.data.local.entity.EducationLocal
import com.caglaakgul.myportfolioapp.data.local.entity.ExperienceLocal
import com.caglaakgul.myportfolioapp.data.local.entity.PortfolioLocal
import com.caglaakgul.myportfolioapp.data.local.entity.ProjectLocal
import com.caglaakgul.myportfolioapp.data.local.entity.TechStackCategoryLocal

object PortfolioSeed {
    val current = PortfolioLocal(
        id = 0,
        about = AboutLocal(
            name = "Çağla Akgül Gürgür",
            title = "Senior Android Developer",
            location = "Istanbul / Türkiye",
            email = "ccaglaakgul@gmail.com",
            phone = "+90 537 816 9226",
            headline = "Senior Android Developer focused on scalable, high-performance mobile applications.",
            github = "https://github.com/caglaakgul",
            linkedin = "https://linkedin.com/in/caglaakgul",
            medium = "https://medium.com/@caglaakgul"
        ),
        experiences = listOf(
            ExperienceLocal(
                company = "Freelance Android Developer",
                role = "Senior Android Developer",
                location = "Remote, Istanbul / Turkey",
                period = "Oct 2025 - Jun 2026",
                summary = "Developed Hemşire Tercih Asistanı as the sole Android developer in a cross-functional team, using Kotlin and Jetpack Compose with a focus on scalable architecture, business logic, and maintainable user flows. Currently preparing for store release.",
                techStack = "Kotlin • Jetpack Compose • Scalable Architecture"
            ),
            ExperienceLocal(
                company = "Avsos via ID3",
                role = "Android Developer",
                location = "Istanbul / Turkey",
                period = "Apr 2025 - Sep 2025",
                summary = "Developed Flight Market, an offline in-flight sales app running on Android POS devices. Built Jetpack Compose-based UI and implemented complex offline sync flows.",
                techStack = "Kotlin • Jetpack Compose • Room • WorkManager • Hilt"
            ),
            ExperienceLocal(
                company = "DoubleTech Studio",
                role = "Freelance Android Developer",
                location = "Prague / Czech Republic",
                period = "Oct 2023 - Jan 2025",
                summary = "Developed Ehliyet Sinav Uygulamasi, a driver’s license exam app using Hilt, Coroutines and Room. Published on Google Play, reaching thousands of users with clean architecture.",
                techStack = "Kotlin • Hilt • Coroutines • Room",
                projectUrl = "https://play.google.com/store/apps/details?id=com.doubletech.esinav&utm_source=emea_Med"
            ),
            ExperienceLocal(
                company = "REM People",
                role = "Android Developer",
                location = "Istanbul / Turkey",
                period = "Sep 2022 - Oct 2023",
                summary = "Refactored legacy Java modules to Kotlin while developing new features for Rem-inStore. Improved code reliability through JUnit and Mockito unit tests across ViewModel and Repository layers.",
                techStack = "Kotlin • MVVM • Hilt • Coroutines • Retrofit • JUnit • Mockito",
                projectUrl = "https://play.google.com/store/search?q=rem%20in%20store&c=apps&utm_source=emea_Med"
            ),
            ExperienceLocal(
                company = "Enerjisa",
                role = "Android Developer",
                location = "Istanbul / Turkey",
                period = "Aug 2021 - Sep 2022",
                summary = "Developed the IKon HR App, enhancing HR workflows for 1000+ employees. Implemented MVVM, Dagger2, DataBinding, and RxJava for scalable architecture.",
                techStack = "MVVM • Dagger2 • DataBinding • RxJava",
                projectUrl = "https://play.google.com/store/apps/details?id=com.enerjisa.hraas&utm_source=emea_Med"
            ),
            ExperienceLocal(
                company = "iPucu Bilisim",
                role = "Android Developer",
                location = "Kocaeli / Turkey",
                period = "May 2020 - Jun 2021",
                summary = "Contributed to ENUYGUN, FUPS, and Tatil.com Android apps using Kotlin. Started as an intern, then promoted to full-time developer after two months.",
                techStack = "Kotlin • Android • Retrofit",
                projectUrl = "https://play.google.com/store/search?q=ENUYGUN&c=apps&utm_source=emea_Med"
            )
        ),
        education = listOf(
            EducationLocal(
                school = "Czech University of Life Sciences Prague",
                degree = "Informatics (Master)",
                location = "Prague / Czech Republic",
                period = "Sep 2024 - Jan 2025",
                summary = "Started the Informatics master’s program in Prague."
            ),
            EducationLocal(
                school = "Prague University of Economics and Business",
                degree = "English Preparatory",
                location = "Prague / Czech Republic",
                period = "Sep 2023 - Jun 2024",
                summary = "Completed English preparation during my study period in Prague."
            ),
            EducationLocal(
                school = "Bilecik University",
                degree = "Computer Engineering (Bachelor)",
                location = "Bilecik / Turkey",
                period = "Sep 2016 - Jun 2020",
                summary = "Learned core computer science fundamentals and focused on mobile development."
            )
        ),
        techStack = listOf(
            TechStackCategoryLocal(
                category = "Programming Languages",
                items = listOf("Kotlin", "Java", "Swift", "SQL")
            ),
            TechStackCategoryLocal(
                category = "Android",
                items = listOf("Jetpack Compose", "MVVM", "Hilt", "Coroutines", "Room", "Retrofit", "WorkManager", "RxJava")
            ),
            TechStackCategoryLocal(
                category = "iOS",
                items = listOf("SwiftUI", "Combine", "StoreKit 2", "UserDefaults")
            ),
            TechStackCategoryLocal(
                category = "Testing",
                items = listOf("JUnit", "Mockito", "Unit Testing")
            ),
            TechStackCategoryLocal(
                category = "Tools",
                items = listOf("Git", "CI/CD", "Firebase", "Postman", "Android Studio", "Xcode", "ChatGPT", "Claude", "Cursor", "Codex")
            )
        ),
        projects = listOf(
            ProjectLocal(
                id = "ehliyet_ios",
                name = "Ehliyet Sınavına Hazırlık 2026",
                description = "Independently developed and published a SwiftUI exam prep app with a JSON-driven quiz engine, media-based questions, progress tracking, StoreKit 2 subscriptions, rewarded ads, and Firebase Crashlytics.",
                techStack = listOf("SwiftUI", "StoreKit 2", "Firebase Crashlytics"),
                playStoreUrl = "https://apps.apple.com/us/app/ehliyet-s%C4%B1nav%C4%B1na-haz%C4%B1rl%C4%B1k-2026/id6779114318",
                githubUrl = null,
                category = "personal",
                company = null
            ),
            ProjectLocal(
                id = "stop_overthinking",
                name = "03:17 - Stop Overthinking",
                description = "Independently developed and published a SwiftUI sleep app with StoreKit 2 purchases, Combine-based state handling, and lightweight UserDefaults persistence.",
                techStack = listOf("SwiftUI", "Combine", "UserDefaults"),
                playStoreUrl = "https://apps.apple.com/us/app/03-17-stop-overthinking/id6760342606",
                githubUrl = null,
                category = "personal",
                company = null
            ),
            ProjectLocal(
                id = "cocktailist",
                name = "Cocktailist",
                description = "Personal project built with Kotlin, MVVM, Hilt, Coroutines, Room, and Firebase, focused on robust data management and a seamless user experience.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Room", "Firebase"),
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.cocktailist&utm_source=emea_Med",
                githubUrl = null,
                category = "personal",
                company = null
            ),
            ProjectLocal(
                id = "quickbite",
                name = "QuickBite",
                description = "Showcases complete app lifecycle management with Kotlin, MVVM, Hilt, Coroutines, and Firebase Realtime DB.",
                techStack = listOf("Kotlin", "MVVM", "Hilt", "Firebase Realtime DB"),
                playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.quickbite&utm_source=emea_Med",
                githubUrl = null,
                category = "personal",
                company = null
            ),
            ProjectLocal(
                id = "talknative",
                name = "TalkNative",
                description = "Android app enabling real-time translation for multilingual texting using Yandex Translate API, with login, registration, discovery, and messaging screens.",
                techStack = listOf("Kotlin", "MVVM", "Dagger2", "RxJava", "Retrofit"),
                playStoreUrl = null,
                githubUrl = "https://github.com/caglaakgul/Talk-Native",
                category = "personal",
                company = null
            )
        )
    )
}
