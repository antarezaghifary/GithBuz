package com.githbuz.presentation.search

import com.githbuz.domain.model.User

sealed class SearchUiState {
    object Loading : SearchUiState()
    data class Success(val users: List<User>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}
