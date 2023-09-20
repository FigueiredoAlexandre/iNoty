package com.example.common.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.common.presentation.viewmodel.INotyViewModel
import com.example.common.presentation.viewmodel.action.UIAction
import com.example.common.presentation.viewmodel.state.UIState

inline fun <State : UIState, Action : UIAction> LifecycleOwner.onStateChanged(
    viewModel: INotyViewModel<State, Action>,
    crossinline onStateChange: (State) -> Unit
) {
    viewModel.state.observe(this, Observer { state -> onStateChange(state) })
}

inline fun <State : UIState, Action : UIAction> LifecycleOwner.onAction(
    viewModel: INotyViewModel<State, Action>,
    crossinline onActionEmited: (Action) -> Unit
) {
    viewModel.action.observe(this, Observer { action -> onActionEmited(action) })
}