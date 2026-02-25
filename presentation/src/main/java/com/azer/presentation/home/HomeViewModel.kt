package com.azer.presentation.home

//import androidx.lifecycle.viewModelScope
//import com.nutapos.core.base.BaseViewModel
//import com.nutapos.domain.usecase.GetUserUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class FinanceViewModel @Inject constructor(
//    private val getUserUseCase: GetUserUseCase
//) : BaseViewModel<FinanceUiState, FinanceUiEvent>(FinanceUiState.Loading) {
//    /**
//     * Triggered when the Fragment starts or user performs a refresh.
//     */
//    fun loadData(userId: Int) {
//        viewModelScope.launch {
//            updateState { FinanceUiState.Loading }
//            getUserUseCase(userId)
//                .catch { exception ->
//                    updateState {
//                        FinanceUiState.Error(exception.message ?: "An unexpected error occurred")
//                    }
//                }
//                .collect { user ->
//                    if (user != null) {
//                        updateState { FinanceUiState.Success(listOf(user)) }
//                    } else {
//                        updateState { FinanceUiState.Error("User not found") }
//                    }
//                }
//        }
//    }
//}