package com.example.mylibrary.presentation.viewmodel.state

import com.example.common.presentation.viewmodel.state.UIState
import com.example.mylibrary.domain.model.DiaryListItem

internal data class DiaryListState(
    val diaries: List<DiaryListItem> = listOf(),
    val isLoading: Boolean = false
) : UIState {

    fun showLoading() = this.copy(isLoading = true)
    fun hideLoading() = this.copy(isLoading = false)

    fun successState(diaries: List<DiaryListItem>) =
        this.copy(diaries = diaries)
}