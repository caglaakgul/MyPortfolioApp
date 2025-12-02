package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.data.remote.FakeProjectApi
import com.caglaakgul.myportfolioapp.data.remote.ProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideProjectApi(): ProjectApi {
        return FakeProjectApi()
    }
}