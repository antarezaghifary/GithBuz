package com.azer.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Event>(initialState: State) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    // For one-time actions like Navigation or Toasts
    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    protected fun sendEvent(event: Event) {
        viewModelScope.launch { _event.send(event) }
    }

    protected fun updateState(reducer: State.() -> State) {
        _state.value = _state.value.reducer()
    }
}