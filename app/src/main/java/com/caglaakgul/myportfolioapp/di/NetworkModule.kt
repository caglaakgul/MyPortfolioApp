package com.caglaakgul.myportfolioapp.di

import com.caglaakgul.myportfolioapp.data.remote.PortfolioApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.caglaakgul.myportfolioapp.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePortfolioApi(retrofit: Retrofit): PortfolioApi {
        return retrofit.create(PortfolioApi::class.java)
    }
}