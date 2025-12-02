package com.caglaakgul.myportfolioapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglaakgul.myportfolioapp.domain.usecase.GetProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProjectsUseCase: GetProjectsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadProjects()
    }

    fun loadProjects() {
        viewModelScope.launch {
            getProjectsUseCase()
                .onStart {
                    _uiState.value = HomeUiState.Loading
                }
                .catch { throwable ->
                    _uiState.value = HomeUiState.Error(
                        message = throwable.message ?: "Unexpected error"
                    )
                }
                .collect { projects ->
                    _uiState.value = HomeUiState.Success(projects)
                }
        }
    }
}