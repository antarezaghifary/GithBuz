package com.githbuz.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githbuz.domain.usecase.search.SearchUsersUseCase
import com.githbuz.domain.usecase.favorite.SetFavoriteUseCase
import com.githbuz.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<SearchUiState>()
    val uiState: LiveData<SearchUiState> = _uiState

    fun searchUsers(query: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            try {
                val users = searchUsersUseCase(query)
                _uiState.value = SearchUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error(e.message.toString())
            }
        }
    }

    fun setFavorite(user: User) {
        viewModelScope.launch {
            setFavoriteUseCase(user, !user.isFavorite)
        }
    }
}
