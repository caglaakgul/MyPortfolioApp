package com.caglaakgul.myportfolioapp.presentation.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray500
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray700
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun AboutScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            AboutHeader()
            Spacer(modifier = Modifier.height(16.dp))
            AboutIntroSection()
            Spacer(modifier = Modifier.height(24.dp))
            AboutFactsSection()
            Spacer(modifier = Modifier.height(24.dp))
            SkillsSection()
        }
    }
}

@Composable
private fun AboutHeader() {
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
                text = stringResource(id = R.string.about_subtitle),
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
private fun AboutFactsSection() {
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
            value = stringResource(id = R.string.about_fact_location_value)
        )
        FactRow(
            label = stringResource(id = R.string.experience_title),
            value = stringResource(id = R.string.about_fact_experience_value)
        )
        FactRow(
            label = stringResource(id = R.string.about_fact_focus_label),
            value = "Offline-first apps, clean architecture, product quality"
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
private fun SkillsSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Core skills",
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
            listOf(
                "Kotlin",
                "Jetpack Compose",
                "Offline-first",
                "Clean Architecture",
                "Coroutines",
                "Dagger Hilt",
                "Room",
                "Retrofit"
            ).forEach { skill ->
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
        AboutScreen()
    }
}