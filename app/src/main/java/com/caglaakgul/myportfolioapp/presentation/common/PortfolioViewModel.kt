package com.caglaakgul.myportfolioapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglaakgul.myportfolioapp.domain.usecase.GetPortfolioUseCase
import com.caglaakgul.myportfolioapp.domain.usecase.RefreshPortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val refreshPortfolioUseCase: RefreshPortfolioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Loading)
    val uiState: StateFlow<PortfolioUiState> = _uiState

    init {
        observePortfolio()
    }

    private fun observePortfolio() {
        viewModelScope.launch {
            getPortfolioUseCase().collect { portfolio ->
                if (portfolio != null) {
                    _uiState.value = PortfolioUiState.Success(portfolio)
                } else {
                    if (_uiState.value !is PortfolioUiState.Success &&
                        _uiState.value !is PortfolioUiState.Error
                    ) {
                        refresh()
                    }
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                if (_uiState.value !is PortfolioUiState.Success) {
                    _uiState.value = PortfolioUiState.Loading
                }
                refreshPortfolioUseCase()
            } catch (e: Exception) {
                if (_uiState.value !is PortfolioUiState.Success) {
                    _uiState.value = PortfolioUiState.Error(
                        e.message ?: "Unexpected error"
                    )
                }
            }
        }
    }
}