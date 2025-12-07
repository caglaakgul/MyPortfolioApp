package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    fun provideGithubToken(): String = BuildConfig.GITHUB_TOKEN
}