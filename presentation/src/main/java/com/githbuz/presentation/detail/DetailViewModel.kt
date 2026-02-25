package com.githbuz.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githbuz.domain.model.User
import com.githbuz.domain.usecase.AddFavoriteUserUseCase
import com.githbuz.domain.usecase.GetUserUseCase
import com.githbuz.domain.usecase.IsFavoriteUserUseCase
import com.githbuz.domain.usecase.RemoveFavoriteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val addFavoriteUserUseCase: AddFavoriteUserUseCase,
    private val removeFavoriteUserUseCase: RemoveFavoriteUserUseCase,
    private val isFavoriteUserUseCase: IsFavoriteUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState?>(null)
    val uiState: StateFlow<DetailUiState?> = _uiState

    fun getUser(username: String) {
        viewModelScope.launch {
            getUserUseCase(username)
                .catch { _uiState.value = DetailUiState.Error(it.message.orEmpty()) }
                .collect { result ->
                    result.onSuccess { user ->
                        _uiState.value = DetailUiState.Success(user)
                    }.onFailure {
                        _uiState.value = DetailUiState.Error(it.message.orEmpty())
                    }
                }
        }
    }

    fun isFavorite(id: Int) {
        viewModelScope.launch {
            isFavoriteUserUseCase(id)
                .catch { _uiState.value = DetailUiState.Error(it.message.orEmpty()) }
                .collect { isFavorite ->
                    _uiState.value = DetailUiState.Favorite(isFavorite)
                }
        }
    }

    fun toggleFavorite(user: User, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                removeFavoriteUserUseCase(user)
            } else {
                addFavoriteUserUseCase(user)
            }
        }
    }
}
