package com.githbuz.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githbuz.domain.model.User
import com.githbuz.domain.usecase.AddFavoriteUserUseCase
import com.githbuz.domain.usecase.GetFavoriteUsersUseCase
import com.githbuz.domain.usecase.IsFavoriteUserUseCase
import com.githbuz.domain.usecase.RemoveFavoriteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase,
    private val isFavoriteUserUseCase: IsFavoriteUserUseCase,
    private val addFavoriteUserUseCase: AddFavoriteUserUseCase,
    private val removeFavoriteUserUseCase: RemoveFavoriteUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Empty)
    val uiState: StateFlow<FavoriteUiState> = _uiState

    fun getFavoriteUsers() {
        viewModelScope.launch {
            getFavoriteUsersUseCase()
                .onStart { _uiState.value = FavoriteUiState.Loading }
                .catch { _uiState.value = FavoriteUiState.Error(it.message.orEmpty()) }
                .collect { users ->
                    _uiState.value = if (users.isEmpty()) {
                        FavoriteUiState.Empty
                    } else {
                        FavoriteUiState.Success(users)
                    }
                }
        }
    }

    fun isFavoriteUser(id: Int): Flow<Boolean> = isFavoriteUserUseCase(id)

    fun addFavoriteUser(user: User) {
        viewModelScope.launch {
            addFavoriteUserUseCase(user)
        }
    }

    fun removeFavoriteUser(user: User) {
        viewModelScope.launch {
            removeFavoriteUserUseCase(user)
        }
    }
}
