package com.caglaakgul.myportfolioapp.presentation.common

import com.caglaakgul.myportfolioapp.domain.model.Portfolio

sealed class PortfolioUiState {
    data object Loading : PortfolioUiState()
    data class Success(val data: Portfolio) : PortfolioUiState()
    data class Error(val message: String) : PortfolioUiState()
}