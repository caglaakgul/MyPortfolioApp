package com.caglaakgul.myportfolioapp.presentation.education

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.domain.model.Education
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioUiState
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioViewModel
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun EducationScreen(
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        when (uiState) {
            is PortfolioUiState.Loading -> {
                BoxWithCenteredLoader()
            }

            is PortfolioUiState.Error -> {
                val error = uiState as PortfolioUiState.Error
                EducationErrorState(
                    message = error.message,
                    onRetryClick = { viewModel.refresh() }
                )
            }

            is PortfolioUiState.Success -> {
                val portfolio = (uiState as PortfolioUiState.Success).data

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.safeDrawing.asPaddingValues())
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    ScreenHeader(
                        title = stringResource(R.string.education_title),
                        subtitle = "Where I studied and the academic path that shaped my engineering mindset."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    EducationIntroSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    EducationListSection(
                        education = portfolio.education
                    )
                }
            }
        }
    }
}

@Composable
private fun BoxWithCenteredLoader() {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EducationErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    androidx.compose.foundation.layout.Box(
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
                text = "Failed to load education",
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
private fun EducationIntroSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.education_intro_primary),
            style = MaterialTheme.typography.bodyMedium,
            color = Gray900
        )
        Text(
            text = stringResource(id = R.string.education_intro_secondary),
            style = MaterialTheme.typography.bodyMedium,
            color = Gray600
        )
    }
}

@Composable
private fun EducationListSection(
    education: List<Education>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        education.forEach { item ->
            EducationCard(item = item)
        }
    }
}

@Composable
private fun EducationCard(
    item: Education
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
                text = item.degree,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Gray900
            )
            Text(
                text = item.school,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray800
            )
            Text(
                text = "${item.location} • ${item.period}",
                style = MaterialTheme.typography.bodySmall,
                color = Gray600
            )

            item.summary.takeIf { it.isNotBlank() }?.let { summary ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray800
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EducationScreenPreview() {
    MyPortfolioAppTheme {
        val fakeEducation = listOf(
            Education(
                school = "Czech University of Life Sciences Prague",
                degree = "MSc - Informatics (Incomplete)",
                location = "Prague, Czech Republic",
                period = "Sep 2024 – Jan 2025",
                summary = "Started the MSc Informatics program; discontinued after one semester."
            ),
            Education(
                school = "Bilecik University",
                degree = "BSc - Computer Engineering",
                location = "Bilecik, Turkey",
                period = "Sep 2016 – Jun 2020",
                summary = "Learned core computer science fundamentals and focused on mobile development."
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
                    title = "Education",
                    subtitle = "Where I studied and the academic path that shaped my engineering mindset."
                )
                Spacer(modifier = Modifier.height(16.dp))
                EducationIntroSection()
                Spacer(modifier = Modifier.height(24.dp))
                EducationListSection(education = fakeEducation)
            }
        }
    }
}