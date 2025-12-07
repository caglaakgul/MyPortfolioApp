package com.caglaakgul.myportfolioapp.di

import android.content.Context
import androidx.room.Room
import com.caglaakgul.myportfolioapp.data.local.AppDatabase
import com.caglaakgul.myportfolioapp.data.local.dao.PortfolioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_portfolio_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePortfolioDao(
        db: AppDatabase
    ): PortfolioDao = db.portfolioDao()
}
