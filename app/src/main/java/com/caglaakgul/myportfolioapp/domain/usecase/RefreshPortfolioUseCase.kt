package com.caglaakgul.myportfolioapp.domain.usecase

import com.caglaakgul.myportfolioapp.domain.repository.PortfolioRepository

class RefreshPortfolioUseCase(
    private val repository: PortfolioRepository
) {
    suspend operator fun invoke() {
        repository.refreshPortfolio()
    }
}