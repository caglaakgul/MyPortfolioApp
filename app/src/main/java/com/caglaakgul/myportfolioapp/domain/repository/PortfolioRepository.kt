package com.caglaakgul.myportfolioapp.domain.repository

import com.caglaakgul.myportfolioapp.domain.model.Portfolio
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun observePortfolio(): Flow<Portfolio?>
    suspend fun refreshPortfolio()
}