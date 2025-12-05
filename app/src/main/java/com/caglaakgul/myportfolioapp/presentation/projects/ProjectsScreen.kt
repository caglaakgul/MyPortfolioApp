package com.caglaakgul.myportfolioapp.presentation.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.domain.model.ProjectCategory
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.components.PrimaryButton
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.home.HomeUiState
import com.caglaakgul.myportfolioapp.presentation.home.HomeViewModel
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray700
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme
import kotlinx.coroutines.launch

@Composable
fun ProjectsScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedFilter by remember { mutableStateOf(ProjectFilter.ALL) }

    val uriHandler = LocalUriHandler.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        ProjectsContent(
            uiState = uiState,
            selectedFilter = selectedFilter,
            onFilterChange = { selectedFilter = it },
            onProjectClick = { project ->
                when {
                    !project.playStoreUrl.isNullOrBlank() -> {
                        uriHandler.openUri(project.playStoreUrl)
                    }

                    !project.githubUrl.isNullOrBlank() -> {
                        uriHandler.openUri(project.githubUrl)
                    }

                    else -> {
                        scope.launch {
                            snackbarHostState.showSnackbar("No public link available")
                        }
                    }
                }
            },
            onRetryClick = { viewModel.loadProjects() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ProjectsContent(
    uiState: HomeUiState,
    selectedFilter: ProjectFilter,
    onFilterChange: (ProjectFilter) -> Unit,
    onProjectClick: (Project) -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            ScreenHeader(
                title = "Projects",
                subtitle = "Apps I’ve built, shipped, maintained and grown."
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProjectsFilterRow(
                selectedFilter = selectedFilter,
                onFilterChange = onFilterChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (uiState) {
                is HomeUiState.Loading -> LoadingState()
                is HomeUiState.Error -> ErrorState(uiState.message, onRetryClick)
                is HomeUiState.Success -> {
                    val filtered = uiState.projects.filter { project ->
                        when (selectedFilter) {
                            ProjectFilter.ALL -> true
                            ProjectFilter.PERSONAL ->
                                project.category == ProjectCategory.PERSONAL

                            ProjectFilter.FREELANCE ->
                                project.category == ProjectCategory.FREELANCE

                            ProjectFilter.PROFESSIONAL ->
                                project.category == ProjectCategory.PROFESSIONAL
                        }
                    }
                    ProjectList(
                        projects = filtered,
                        onProjectClick = onProjectClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectsFilterRow(
    selectedFilter: ProjectFilter,
    onFilterChange: (ProjectFilter) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            label = "All",
            isSelected = selectedFilter == ProjectFilter.ALL,
            onClick = { onFilterChange(ProjectFilter.ALL) }
        )
        FilterChip(
            label = "Personal",
            isSelected = selectedFilter == ProjectFilter.PERSONAL,
            onClick = { onFilterChange(ProjectFilter.PERSONAL) }
        )
        FilterChip(
            label = "Freelance",
            isSelected = selectedFilter == ProjectFilter.FREELANCE,
            onClick = { onFilterChange(ProjectFilter.FREELANCE) }
        )
        FilterChip(
            label = "Professional",
            isSelected = selectedFilter == ProjectFilter.PROFESSIONAL,
            onClick = { onFilterChange(ProjectFilter.PROFESSIONAL) }
        )
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 136.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Failed to load projects",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF333333)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF777777)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(text = "Retry", onClick = onRetryClick)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectList(
    projects: List<Project>,
    onProjectClick: (Project) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = WindowInsets.navigationBarsIgnoringVisibility
            .asPaddingValues()
            .let {
                PaddingValues(
                    top = 8.dp,
                    bottom = 24.dp,
                    start = 0.dp,
                    end = 0.dp
                )
            }
    ) {
        items(projects) { project ->
            ProjectCard(
                project = project,
                onClick = { onProjectClick(project) }
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    onClick: () -> Unit
) {
    val introTop = MaterialTheme.colorScheme.primary.copy(alpha = 0.95f)
    val introBottom = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
    val gradientMid = lerp(introTop, introBottom, 0.4f)
    val cardColor = lerp(gradientMid, Color.White, 0.8f)

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = cardElevation(0.dp),
        colors = cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = project.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF111827)
                )

                project.company?.let {
                    Text(
                        text = " •  $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray700,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray700
            )

            Text(
                text = project.techStack.joinToString(", "),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF4B5563)
            )
        }
    }
}

private val sampleProjectsPreview = listOf(
    Project(
        id = "cocktailist",
        name = "Cocktailist",
        description = "Modern cocktail recipe app with offline support.",
        techStack = listOf("Kotlin", "Compose", "Room", "Hilt", "Firebase"),
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.cocktailist",
        githubUrl = null,
        category = ProjectCategory.PERSONAL
    ),
    Project(
        id = "ehliyet",
        name = "Ehliyet Sinav Uygulamasi",
        description = "Driver’s license exam prep app with 2025 questions.",
        techStack = listOf("Kotlin", "Clean Arch", "Hilt", "Room"),
        playStoreUrl = null,
        githubUrl = null,
        category = ProjectCategory.FREELANCE
    ),
    Project(
        id = "flight_market",
        name = "Flight Market",
        description = "Offline in-flight retail app for SunExpress crews.",
        techStack = listOf("Kotlin", "Compose", "Room", "WorkManager"),
        playStoreUrl = null,
        githubUrl = null,
        category = ProjectCategory.PROFESSIONAL,
        company = "EnerjiSa"
    )
)

@Preview(showBackground = true)
@Composable
fun ProjectsSuccessPreview() {
    MyPortfolioAppTheme {
        var filter by remember { mutableStateOf(ProjectFilter.ALL) }
        ProjectsContent(
            uiState = HomeUiState.Success(sampleProjectsPreview),
            selectedFilter = filter,
            onFilterChange = { filter = it },
            onProjectClick = {},
            onRetryClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsLoadingPreview() {
    MyPortfolioAppTheme {
        var filter by remember { mutableStateOf(ProjectFilter.ALL) }
        ProjectsContent(
            uiState = HomeUiState.Loading,
            selectedFilter = filter,
            onFilterChange = { filter = it },
            onProjectClick = {},
            onRetryClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsErrorPreview() {
    MyPortfolioAppTheme {
        var filter by remember { mutableStateOf(ProjectFilter.ALL) }
        ProjectsContent(
            uiState = HomeUiState.Error("Network error"),
            selectedFilter = filter,
            onFilterChange = { filter = it },
            onProjectClick = {},
            onRetryClick = {},
            modifier = Modifier
        )
    }
}