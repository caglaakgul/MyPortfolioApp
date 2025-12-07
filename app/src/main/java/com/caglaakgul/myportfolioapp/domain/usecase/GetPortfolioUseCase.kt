package com.caglaakgul.myportfolioapp.domain.usecase

import com.caglaakgul.myportfolioapp.domain.model.Portfolio
import com.caglaakgul.myportfolioapp.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

class GetPortfolioUseCase(
    private val repository: PortfolioRepository
) {
    operator fun invoke(): Flow<Portfolio?> {
        return repository.observePortfolio()
    }
}