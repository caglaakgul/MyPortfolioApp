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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun EducationScreen() {
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
            ScreenHeader(
                title = stringResource(R.string.education_title),
                subtitle = "Where I studied and the academic path that shaped my engineering mindset."
            )
            Spacer(modifier = Modifier.height(16.dp))
            EducationIntroSection()
            Spacer(modifier = Modifier.height(24.dp))
            EducationListSection()
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
private fun EducationListSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        educationItems.forEach { item ->
            EducationCard(item = item)
        }
    }
}

@Composable
private fun EducationCard(
    item: EducationUiModel
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

            item.summary?.takeIf { it.isNotBlank() }?.let { summary ->
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
        EducationScreen()
    }
}