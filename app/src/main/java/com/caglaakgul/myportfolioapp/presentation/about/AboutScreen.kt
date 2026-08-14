package com.caglaakgul.myportfolioapp.presentation.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.domain.model.About
import com.caglaakgul.myportfolioapp.domain.model.Portfolio
import com.caglaakgul.myportfolioapp.domain.model.TechStackCategory
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioUiState
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioViewModel
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray500
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray700
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun AboutScreen(
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
                AboutErrorState(
                    message = error.message,
                    onRetryClick = { viewModel.refresh() }
                )
            }

            is PortfolioUiState.Success -> {
                val portfolio = (uiState as PortfolioUiState.Success).data
                val about = portfolio.about

                // Core skills: techStack listesinden ilk birkaç item
                val coreSkills = portfolio.techStack
                    .flatMap { it.items }
                    .distinct()
                    .take(8)
                    .ifEmpty {
                        stringArrayResource(id = R.array.about_core_skills_fallback).toList()
                    }

                AboutContent(
                    about = about,
                    coreSkills = coreSkills
                )
            }
        }
    }
}

@Composable
private fun AboutErrorState(
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
                text = stringResource(id = R.string.about_error_title),
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
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}

@Composable
private fun AboutContent(
    about: About,
    coreSkills: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        AboutHeader(headline = about.headline)
        Spacer(modifier = Modifier.height(16.dp))
        AboutIntroSection()
        Spacer(modifier = Modifier.height(24.dp))
        AboutFactsSection(about = about)
        Spacer(modifier = Modifier.height(24.dp))
        SkillsSection(coreSkills = coreSkills)
    }
}

@Composable
private fun AboutHeader(
    headline: String
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(600)) + slideInVertically(
            tween(600, easing = FastOutSlowInEasing)
        ) { full -> full / 3 }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.96f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = stringResource(id = R.string.about_title),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                color = Gray800
            )
            Text(
                text = if (headline.isNotBlank()) headline
                else stringResource(id = R.string.about_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = Gray600
            )
        }
    }
}

@Composable
private fun AboutIntroSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_intro_line1),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF111827)
        )
        Text(
            text = stringResource(id = R.string.about_intro_line2),
            style = MaterialTheme.typography.bodyMedium,
            color = Gray700
        )
    }
}

@Composable
private fun AboutFactsSection(
    about: About
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_quick_facts),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
            ),
            color = Gray900
        )
        Spacer(modifier = Modifier.height(2.dp))
        FactRow(
            label = stringResource(id = R.string.about_fact_location_label),
            value = about.location
        )
        FactRow(
            label = stringResource(id = R.string.experience_title),
            value = stringResource(id = R.string.about_fact_experience_value)
        )
        FactRow(
            label = stringResource(id = R.string.about_fact_focus_label),
            value = stringResource(id = R.string.about_fact_focus_value)
        )
    }
}

@Composable
private fun FactRow(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Gray500
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF111827)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection(
    coreSkills: List<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_core_skills),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
            ),
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(2.dp))

        androidx.compose.foundation.layout.FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            coreSkills.forEach { skill ->
                FilterChip(
                    label = skill,
                    isSelected = false,
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MyPortfolioAppTheme {
        val fakeAbout = About(
            name = stringResource(id = R.string.preview_name),
            title = stringResource(id = R.string.preview_title),
            location = stringResource(id = R.string.preview_location),
            email = stringResource(id = R.string.preview_email),
            phone = "",
            headline = stringResource(id = R.string.preview_headline),
            github = stringResource(id = R.string.preview_github),
            linkedin = stringResource(id = R.string.preview_linkedin),
            medium = stringResource(id = R.string.preview_medium)
        )

        val fakeTechStack = listOf(
            TechStackCategory(
                category = stringResource(id = R.string.category_programming_languages),
                items = stringArrayResource(id = R.array.preview_programming_language_items).toList()
            ),
            TechStackCategory(
                category = stringResource(id = R.string.category_android),
                items = stringArrayResource(id = R.array.preview_android_items).toList()
            )
        )

        val fakePortfolio = Portfolio(
            about = fakeAbout,
            experiences = emptyList(),
            education = emptyList(),
            techStack = fakeTechStack,
            projects = emptyList()
        )

        AboutContent(
            about = fakePortfolio.about,
            coreSkills = fakePortfolio.techStack.flatMap { it.items }.distinct().take(8)
        )
    }
}
