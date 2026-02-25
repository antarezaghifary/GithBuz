package com.githbuz.presentation.favorite

import com.githbuz.domain.model.User

sealed class FavoriteUiState {
    object Loading : FavoriteUiState()
    object Empty : FavoriteUiState()
    data class Success(val users: List<User>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}
