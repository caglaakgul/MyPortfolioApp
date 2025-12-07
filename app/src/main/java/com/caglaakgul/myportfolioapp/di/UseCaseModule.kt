package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.domain.repository.PortfolioRepository
import com.caglaakgul.myportfolioapp.domain.usecase.GetPortfolioUseCase
import com.caglaakgul.myportfolioapp.domain.usecase.RefreshPortfolioUseCase
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
    fun provideGetPortfolioUseCase(
        repository: PortfolioRepository
    ): GetPortfolioUseCase = GetPortfolioUseCase(repository)

    @Provides
    @Singleton
    fun provideRefreshPortfolioUseCase(
        repository: PortfolioRepository
    ): RefreshPortfolioUseCase = RefreshPortfolioUseCase(repository)
}