package com.azer.presentation.search

import androidx.lifecycle.viewModelScope
import com.azer.core.base.BaseViewModel
import com.azer.domain.model.User
import com.azer.domain.usecase.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SearchUiEvent {
    data class NavigateToDetail(val username: String) : SearchUiEvent()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>(SearchUiState.Idle) {

    fun searchUsers(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            updateState { SearchUiState.Loading }
            searchUserUseCase(query).collect { result ->
                result.fold(
                    onSuccess = { users ->
                        updateState { SearchUiState.Success(users) }
                    },
                    onFailure = { error ->
                        updateState { SearchUiState.Error(error.message ?: "Unknown error") }
                    }
                )
            }
        }
    }

    fun onUserClicked(user: User) {
        sendEvent(SearchUiEvent.NavigateToDetail(user.login))
    }
}
