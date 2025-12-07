package com.caglaakgul.myportfolioapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.caglaakgul.myportfolioapp.data.local.converter.PortfolioConverters
import com.caglaakgul.myportfolioapp.data.local.dao.PortfolioDao
import com.caglaakgul.myportfolioapp.data.local.entity.PortfolioLocal

@Database(
    entities = [PortfolioLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PortfolioConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}