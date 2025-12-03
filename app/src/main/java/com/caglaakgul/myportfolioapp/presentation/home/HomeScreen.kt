package com.caglaakgul.myportfolioapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun HomeScreen(
    onProjectsClick: () -> Unit,
    onAboutClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.95f),
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                HomeHeader()

                Spacer(modifier = Modifier.height(24.dp))

                HomeSectionList(
                    onProjectsClick = onProjectsClick,
                    onExperienceClick = { },
                    onTechStackClick = { },
                    onEducationClick = {},
                    onAboutClick = onAboutClick
                )
            }
        }
    }
}

@Composable
private fun HomeHeader() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(600)) + slideInVertically(
            tween(600, easing = FastOutSlowInEasing)
        ) { full -> full / 4 }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 48.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.home_description),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.85f)
            )
        }
    }
}

@Composable
private fun HomeSectionList(
    onProjectsClick: () -> Unit,
    onExperienceClick: () -> Unit,
    onTechStackClick: () -> Unit,
    onEducationClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionCard(
            title = stringResource(id = R.string.projects_title),
            description = stringResource(id = R.string.home_projects_description),
            onClick = onProjectsClick
        )
        SectionCard(
            title = stringResource(id = R.string.experience_title),
            description = stringResource(id = R.string.home_experience_description),
            onClick = onExperienceClick
        )
        SectionCard(
            title = stringResource(id = R.string.techstack_title),
            description = stringResource(id = R.string.home_techstack_description),
            onClick = onTechStackClick
        )
        SectionCard(
            title = stringResource(id = R.string.education_title),
            description =stringResource(id = R.string.home_education_description),
            onClick = onEducationClick
        )
        SectionCard(
            title = stringResource(id = R.string.about_title),
            description = stringResource(id = R.string.home_about_description),
            onClick = onAboutClick
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.10f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.85f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyPortfolioAppTheme {
        HomeScreen(
            onProjectsClick = {},
            onAboutClick = {}
        )
    }
}