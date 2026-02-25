package com.azer.presentation.detail

import com.azer.domain.model.User

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val user: User) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}
