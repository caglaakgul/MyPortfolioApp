package com.caglaakgul.myportfolioapp.presentation.skills

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun TechStackScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ScreenHeader(
                title = "Tech Stack",
                subtitle = "The languages, tools and frameworks I use to design, build and ship Android apps."
            )

            Spacer(modifier = Modifier.height(16.dp))
            TechStackIntroSection()
            Spacer(modifier = Modifier.height(24.dp))
            TechStackContentSection()
        }
    }
}

@Composable
private fun TechStackIntroSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "I mainly work with Kotlin and modern Android tools like Jetpack Compose, Hilt and Coroutines. " +
                    "I care a lot about clean architecture, testability and maintainable codebases.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray900
        )
        Text(
            text = "You can quickly explore what I use in production through the categories below.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray600
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TechStackContentSection() {
    if (techStackItems.isEmpty()) return

    var selectedCategory by remember { mutableStateOf(techStackItems.first().category) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            techStackItems.forEach { category ->
                FilterChip(
                    label = category.category,
                    isSelected = category.category == selectedCategory,
                    onClick = { selectedCategory = category.category }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val selected = techStackItems.firstOrNull { it.category == selectedCategory }
            ?: techStackItems.first()

        AnimatedContent(
            targetState = selected,
            label = "techStackCategoryChange"
        ) { category ->
            TechStackCategoryCard(category = category)
        }
    }
}


@Composable
private fun TechStackCategoryCard(
    category: TechStackUiModel
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = cardColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFFF9FAFB)
        ),
        elevation = cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = category.category,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Gray900
            )

            category.items.forEach { tech ->
                Text(
                    text = "• $tech",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray800
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechStackScreenPreview() {
    MyPortfolioAppTheme {
        TechStackScreen()
    }
}