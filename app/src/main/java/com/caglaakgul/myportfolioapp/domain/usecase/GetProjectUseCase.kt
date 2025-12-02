package com.caglaakgul.myportfolioapp.domain.usecase

import com.caglaakgul.myportfolioapp.domain.repository.ProjectRepository

class GetProjectsUseCase(
    private val repository: ProjectRepository
) {
    operator fun invoke() = repository.getProjects()
}