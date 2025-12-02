package com.caglaakgul.myportfolioapp.domain.repository

import com.caglaakgul.myportfolioapp.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getProjects(): Flow<List<Project>>
    fun getProjectById(id: String): Flow<Project?>
}