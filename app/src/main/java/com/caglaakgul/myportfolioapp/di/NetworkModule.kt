package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.data.remote.FakeProjectApi
import com.caglaakgul.myportfolioapp.data.remote.ProjectApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    fun provideProjectApi(): ProjectApi {
        return FakeProjectApi()
    }
}