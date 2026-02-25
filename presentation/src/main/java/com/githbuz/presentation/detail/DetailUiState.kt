package com.githbuz.presentation.detail

import com.githbuz.domain.model.User

sealed class DetailUiState {
    data class Success(val user: User) : DetailUiState()
    data class Favorite(val isFavorite: Boolean) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}
