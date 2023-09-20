package com.example.mylibrary.presentation.viewmodel.action

import com.example.common.presentation.viewmodel.action.UIAction

internal sealed class DiaryListAction : UIAction {
    object NavigateToDiaryCreation : DiaryListAction()
    object EndRefreshing : DiaryListAction()
}