package com.caglaakgul.myportfolioapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caglaakgul.myportfolioapp.data.local.entity.PortfolioLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolio LIMIT 1")
    fun observePortfolio(): Flow<PortfolioLocal?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolio(portfolio: PortfolioLocal)

    @Query("DELETE FROM portfolio")
    suspend fun clearPortfolio()
}