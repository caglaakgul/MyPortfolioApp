package com.caglaakgul.myportfolioapp.presentation.home

import com.caglaakgul.myportfolioapp.domain.model.Project

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val projects: List<Project>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}