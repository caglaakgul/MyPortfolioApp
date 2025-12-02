package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.data.remote.ProjectApi
import com.caglaakgul.myportfolioapp.data.repository.ProjectRepositoryImpl
import com.caglaakgul.myportfolioapp.domain.repository.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProjectRepository(
        api: ProjectApi
    ): ProjectRepository {
        return ProjectRepositoryImpl(api)
    }
}