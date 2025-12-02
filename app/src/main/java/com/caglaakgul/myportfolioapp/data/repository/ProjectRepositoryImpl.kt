package com.caglaakgul.myportfolioapp.data.repository

import com.caglaakgul.myportfolioapp.data.mapper.toDomain
import com.caglaakgul.myportfolioapp.data.remote.ProjectApi
import com.caglaakgul.myportfolioapp.domain.model.Project
import com.caglaakgul.myportfolioapp.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProjectRepositoryImpl(
    private val api: ProjectApi
) : ProjectRepository {

    override fun getProjects(): Flow<List<Project>> = flow {
        val remote = api.getProjects().map { it.toDomain() }
        emit(remote)
    }

    override fun getProjectById(id: String): Flow<Project?> = flow {
        val remote = api.getProjects().map { it.toDomain() }
        emit(remote.firstOrNull { it.id == id })
    }
}