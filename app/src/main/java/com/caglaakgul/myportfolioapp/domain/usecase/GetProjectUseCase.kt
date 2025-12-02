package com.caglaakgul.myportfolioapp.domain.usecase

import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow

class GetProjectsUseCase(
    private val repository: ProjectRepository
) {
    operator fun invoke(): Flow<List<Project>> {
        return repository.getProjects()
    }
}