package com.azer.presentation.home

sealed class HomeUiEvent {
    data class ShowToast(val message: String) : HomeUiEvent()
    object NavigateToDetails : HomeUiEvent()
}