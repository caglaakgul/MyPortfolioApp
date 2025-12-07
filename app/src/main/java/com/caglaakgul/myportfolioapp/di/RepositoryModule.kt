package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.data.local.dao.PortfolioDao
import com.caglaakgul.myportfolioapp.data.remote.PortfolioApi
import com.caglaakgul.myportfolioapp.data.repository.PortfolioRepositoryImpl
import com.caglaakgul.myportfolioapp.domain.repository.PortfolioRepository
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
    fun providePortfolioRepository(
        api: PortfolioApi,
        dao: PortfolioDao,
        token: String
    ): PortfolioRepository {
        return PortfolioRepositoryImpl(api, dao, token)
    }
}