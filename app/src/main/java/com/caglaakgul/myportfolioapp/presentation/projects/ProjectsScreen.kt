package com.caglaakgul.myportfolioapp.presentation.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.presentation.home.HomeUiState
import com.caglaakgul.myportfolioapp.presentation.home.HomeViewModel
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme


@Composable
fun ProjectsScreen(
    onProjectClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ProjectsContent(
        uiState = uiState,
        onProjectClick = onProjectClick,
        onRetryClick = { viewModel.loadProjects() }
    )
}

@Composable
private fun ProjectsContent(
    uiState: HomeUiState,
    onProjectClick: (String) -> Unit,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Projects",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Failed to load projects.")
                        Text(
                            text = uiState.message,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Button(onClick = onRetryClick) {
                            Text("Retry")
                        }
                    }
                }
            }

            is HomeUiState.Success -> {
                Spacer(modifier = Modifier.height(16.dp))
                ProjectList(
                    projects = uiState.projects,
                    onProjectClick = onProjectClick
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectList(
    projects: List<Project>,
    onProjectClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = WindowInsets.navigationBarsIgnoringVisibility
            .add(WindowInsets(bottom = 16.dp))
            .asPaddingValues()
    ) {
        items(projects) { project ->
            ProjectItem(
                project = project,
                onClick = { onProjectClick(project.id) }
            )
        }
    }
}

@Composable
private fun ProjectItem(
    project: Project,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = project.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = project.techStack.joinToString(", "),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsSuccessPreview() {
    val sampleProjects = listOf(
        Project(
            id = "cocktailist",
            name = "Cocktailist",
            description = "A modern cocktail recipe app.",
            techStack = listOf("Kotlin", "MVVM", "Coroutines", "Room", "Hilt", "Firebase")
        ),
        Project(
            id = "quickbite",
            name = "QuickBite",
            description = "Food recipe app with category filters and favorites.",
            techStack = listOf("Kotlin", "MVVM", "Coroutines", "Firebase")
        )
    )
    MyPortfolioAppTheme {
        ProjectsContent(
            uiState = HomeUiState.Success(sampleProjects),
            onProjectClick = {},
            onRetryClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsLoadingPreview() {
    MyPortfolioAppTheme {
        ProjectsContent(
            uiState = HomeUiState.Loading,
            onProjectClick = {},
            onRetryClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsErrorPreview() {
    MyPortfolioAppTheme {
        ProjectsContent(
            uiState = HomeUiState.Error("Something went wrong"),
            onProjectClick = {},
            onRetryClick = {}
        )
    }
}