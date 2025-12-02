package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.domain.repository.ProjectRepository
import com.caglaakgul.myportfolioapp.domain.usecase.GetProjectsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProjectUseCase(projectRepository: ProjectRepository): GetProjectsUseCase{
        return GetProjectsUseCase(projectRepository)
    }
}

