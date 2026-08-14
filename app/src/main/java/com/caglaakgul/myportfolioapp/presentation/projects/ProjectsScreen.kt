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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caglaakgul.myportfolioapp.R
import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.domain.model.ProjectCategory
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioUiState
import com.caglaakgul.myportfolioapp.presentation.common.PortfolioViewModel
import com.caglaakgul.myportfolioapp.presentation.components.FilterChip
import com.caglaakgul.myportfolioapp.presentation.components.PrimaryButton
import com.caglaakgul.myportfolioapp.presentation.components.ScreenHeader
import com.caglaakgul.myportfolioapp.presentation.ui.theme.Gray700
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme
import kotlinx.coroutines.launch

@Composable
fun ProjectsScreen(
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var selectedFilter by remember { mutableStateOf(ProjectFilter.ALL) }

    val uriHandler = LocalUriHandler.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val noPublicLinkMessage = stringResource(id = R.string.projects_no_public_link)

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        when (state) {
            is PortfolioUiState.Loading -> {
                ProjectsLoadingScaffold(modifier = Modifier.padding(innerPadding))
            }

            is PortfolioUiState.Error -> {
                ProjectsErrorScaffold(
                    message = (state as PortfolioUiState.Error).message,
                    onRetryClick = { viewModel.refresh() },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is PortfolioUiState.Success -> {
                val portfolio = (state as PortfolioUiState.Success).data
                val projects = portfolio.projects

                ProjectsContent(
                    projects = projects,
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
                                    snackbarHostState.showSnackbar(noPublicLinkMessage)
                                }
                            }
                        }
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun ProjectsContent(
    projects: List<Project>,
    selectedFilter: ProjectFilter,
    onFilterChange: (ProjectFilter) -> Unit,
    onProjectClick: (Project) -> Unit,
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
                title = stringResource(id = R.string.projects_title),
                subtitle = stringResource(id = R.string.projects_subtitle)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProjectsFilterRow(
                selectedFilter = selectedFilter,
                onFilterChange = onFilterChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            val filtered = projects.filter { project ->
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

@Composable
private fun ProjectsLoadingScaffold(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues()),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ProjectsErrorScaffold(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(bottom = 136.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.projects_error_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF333333)
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF777777)
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    text = stringResource(id = R.string.retry),
                    onClick = onRetryClick
                )
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
            label = stringResource(id = R.string.project_filter_all),
            isSelected = selectedFilter == ProjectFilter.ALL,
            onClick = { onFilterChange(ProjectFilter.ALL) }
        )
        FilterChip(
            label = stringResource(id = R.string.project_filter_personal),
            isSelected = selectedFilter == ProjectFilter.PERSONAL,
            onClick = { onFilterChange(ProjectFilter.PERSONAL) }
        )
        FilterChip(
            label = stringResource(id = R.string.project_filter_freelance),
            isSelected = selectedFilter == ProjectFilter.FREELANCE,
            onClick = { onFilterChange(ProjectFilter.FREELANCE) }
        )
        FilterChip(
            label = stringResource(id = R.string.project_filter_professional),
            isSelected = selectedFilter == ProjectFilter.PROFESSIONAL,
            onClick = { onFilterChange(ProjectFilter.PROFESSIONAL) }
        )
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
                        text = stringResource(id = R.string.project_company_format, it),
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

@Composable
private fun sampleProjectsPreview(): List<Project> {
    return listOf(
        Project(
            id = "ehliyet_ios",
            name = stringResource(id = R.string.project_ehliyet_ios_name),
            description = stringResource(id = R.string.project_ehliyet_ios_description),
            techStack = stringArrayResource(id = R.array.project_ehliyet_ios_stack).toList(),
            playStoreUrl = "https://apps.apple.com/us/app/ehliyet-s%C4%B1nav%C4%B1na-haz%C4%B1rl%C4%B1k-2026/id6779114318",
            githubUrl = null,
            category = ProjectCategory.PERSONAL
        ),
        Project(
            id = "sleep_ios",
            name = stringResource(id = R.string.project_sleep_name),
            description = stringResource(id = R.string.project_sleep_description),
            techStack = stringArrayResource(id = R.array.project_sleep_stack).toList(),
            playStoreUrl = "https://apps.apple.com/us/app/03-17-stop-overthinking/id6760342606",
            githubUrl = null,
            category = ProjectCategory.PERSONAL
        ),
        Project(
            id = "cocktailist",
            name = stringResource(id = R.string.project_cocktailist_name),
            description = stringResource(id = R.string.project_cocktailist_description),
            techStack = stringArrayResource(id = R.array.project_cocktailist_stack).toList(),
            playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.cocktailist&utm_source=emea_Med",
            githubUrl = null,
            category = ProjectCategory.PERSONAL
        ),
        Project(
            id = "quickbite",
            name = stringResource(id = R.string.project_quickbite_name),
            description = stringResource(id = R.string.project_quickbite_description),
            techStack = stringArrayResource(id = R.array.project_quickbite_stack).toList(),
            playStoreUrl = "https://play.google.com/store/apps/details?id=com.caglaakgul.quickbite&utm_source=emea_Med",
            githubUrl = null,
            category = ProjectCategory.PERSONAL
        ),
        Project(
            id = "talknative",
            name = stringResource(id = R.string.project_talknative_name),
            description = stringResource(id = R.string.project_talknative_description),
            techStack = stringArrayResource(id = R.array.project_talknative_stack).toList(),
            playStoreUrl = null,
            githubUrl = "https://github.com/caglaakgul/Talk-Native",
            category = ProjectCategory.PERSONAL
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectsSuccessPreview() {
    MyPortfolioAppTheme {
        var filter by remember { mutableStateOf(ProjectFilter.ALL) }
        ProjectsContent(
            projects = sampleProjectsPreview(),
            selectedFilter = filter,
            onFilterChange = { filter = it },
            onProjectClick = { },
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsLoadingPreview() {
    MyPortfolioAppTheme {
        ProjectsLoadingScaffold(
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectsErrorPreview() {
    MyPortfolioAppTheme {
        ProjectsErrorScaffold(
            message = stringResource(id = R.string.preview_network_error),
            onRetryClick = {  },
            modifier = Modifier
        )
    }
}
