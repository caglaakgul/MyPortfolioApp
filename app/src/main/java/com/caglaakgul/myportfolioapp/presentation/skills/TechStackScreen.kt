package com.caglaakgul.myportfolioapp.presentation.skills

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioUiState
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioViewModel
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray600
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray800
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray900
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun TechStackScreen(
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color.White
    ) {
        when (state) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.safeDrawing.asPaddingValues()),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Failed to load tech stack",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Gray900
                    )
                }
            }

            is PortfolioUiState.Success -> {
                val portfolio = (state as PortfolioUiState.Success).data
                val categories = portfolio.techStack.map {
                    TechStackUiModel(
                        category = it.category,
                        items = it.items
                    )
                }

                TechStackContentScreen(techStackItems = categories)
            }
        }
    }
}

@Composable
private fun TechStackContentScreen(
    techStackItems: List<TechStackUiModel>
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
        TechStackContentSection(techStackItems)
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
private fun TechStackContentSection(
    techStackItems: List<TechStackUiModel>
) {
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
        TechStackContentScreen(
            techStackItems = listOf(
                TechStackUiModel(
                    category = "Programming Languages",
                    items = listOf("Kotlin", "Java", "SQL")
                )
            )
        )
    }
}