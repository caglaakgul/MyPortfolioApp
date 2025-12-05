package com.caglaakgul.myportfolioapp.presentation.experience


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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun ExperienceScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ScreenHeader("Experiences", "Where I’ve worked, what I built and what I owned as an Android Developer.")
            Spacer(modifier = Modifier.height(16.dp))
            ExperienceIntroSection()
            Spacer(modifier = Modifier.height(24.dp))
            ExperienceListSection()
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
private fun ExperienceListSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        experienceItems.forEach { item ->
            ExperienceCard(item = item)
        }
    }
}

@Composable
private fun ExperienceCard(
    item: ExperienceUiModel
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
        ExperienceScreen()
    }
}