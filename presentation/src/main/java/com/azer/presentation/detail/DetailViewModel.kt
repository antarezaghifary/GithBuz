package com.azer.presentation.detail

import androidx.lifecycle.viewModelScope
import com.azer.core.base.BaseViewModel
import com.azer.domain.model.User
import com.azer.domain.usecase.GetDetailUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



sealed class DetailUiEvent

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUserUseCase: GetDetailUserUseCase
) : BaseViewModel<DetailUiState, DetailUiEvent>(DetailUiState.Loading) {

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            updateState { DetailUiState.Loading }
            getDetailUserUseCase(username).collect { result ->
                result.fold(
                    onSuccess = { user ->
                        updateState { DetailUiState.Success(user) }
                    },
                    onFailure = { error ->
                        updateState { DetailUiState.Error(error.message ?: "Unknown error") }
                    }
                )
            }
        }
    }
}
