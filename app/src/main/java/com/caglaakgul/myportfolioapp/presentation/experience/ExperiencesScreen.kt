package com.caglaakgul.myportfolioapp.presentation.experience

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.domain.model.Experience
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioUiState
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioViewModel
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun ExperienceScreen(
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        when (uiState) {
            is PortfolioUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.safeDrawing.asPaddingValues()),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is PortfolioUiState.Error -> {
                val error = uiState as PortfolioUiState.Error
                ExperienceErrorState(
                    message = error.message,
                    onRetryClick = { viewModel.refresh() }
                )
            }

            is PortfolioUiState.Success -> {
                val experiences = (uiState as PortfolioUiState.Success).data.experiences

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.safeDrawing.asPaddingValues())
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    ScreenHeader(
                        title = "Experiences",
                        subtitle = "Where I’ve worked, what I built and what I owned as an Android Developer."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExperienceIntroSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    ExperienceListSection(experiences = experiences)
                }
            }
        }
    }
}

@Composable
private fun ExperienceErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Failed to load experiences",
                style = MaterialTheme.typography.titleMedium,
                color = Gray900
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = Gray600
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
private fun ExperienceIntroSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "I’ve been working as an Android Developer for 5+ years, mostly on product-focused " +
                    "apps where clean architecture, offline-first design and performance matter.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray900
        )
        Text(
            text = "Below is a quick overview of the teams I’ve worked with and what I was responsible for.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray600
        )
    }
}

@Composable
private fun ExperienceListSection(
    experiences: List<Experience>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        experiences.forEach { item ->
            ExperienceCard(item = item)
        }
    }
}

@Composable
private fun ExperienceCard(
    item: Experience
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = cardColors(
            containerColor = Color(0xFFF9FAFB)
        ),
        elevation = cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = item.role,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Gray900
            )
            Text(
                text = item.company,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray800
            )
            Text(
                text = "${item.location} • ${item.period}",
                style = MaterialTheme.typography.bodySmall,
                color = Gray600
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray800
            )
            Text(
                text = item.techStack,
                style = MaterialTheme.typography.bodySmall,
                color = Gray600
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExperienceScreenPreview() {
    MyPortfolioAppTheme {
        val fakeExperiences = listOf(
            Experience(
                company = "Avsos via ID3 / SunExpress Flight Market",
                role = "Android Developer",
                location = "Istanbul, Turkey",
                period = "Apr 2025 – Sep 2025",
                summary = "Built an offline in-flight retail app running on Android POS devices using Jetpack Compose.",
                techStack = "Kotlin • Jetpack Compose • Room • WorkManager • Hilt"
            ),
            Experience(
                company = "REM People",
                role = "Android Developer",
                location = "Istanbul, Turkey",
                period = "Sep 2022 – Oct 2023",
                summary = "Developed new features and refactored legacy Java code to Kotlin for Rem-inStore.",
                techStack = "Kotlin • MVVM • Dagger-Hilt • Coroutines • Retrofit"
            )
        )

        Surface(color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ScreenHeader(
                    title = "Experiences",
                    subtitle = "Where I’ve worked, what I built and what I owned as an Android Developer."
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExperienceIntroSection()
                Spacer(modifier = Modifier.height(24.dp))
                ExperienceListSection(experiences = fakeExperiences)
            }
        }
    }
}
