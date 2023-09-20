package com.example.common.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.presentation.viewmodel.action.UIAction
import com.example.common.presentation.viewmodel.state.UIState

open class INotyViewModel<State : UIState, Action : UIAction>(
    private val initialUIState: State
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(initialUIState)
    val state: LiveData<State> = _state

    private val  _action: MutableLiveData<Action> = MutableLiveData()
    val action: LiveData<Action> = _action

    fun setState(block: (State) -> State) {
        _state.value = block(_state.value!!)
    }

    fun sendAction(block: () -> Action) {
        _action.value = block()
    }
}