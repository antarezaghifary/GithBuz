package com.azer.presentation.search

import com.azer.domain.model.User

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val users: List<User>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
