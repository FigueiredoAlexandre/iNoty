package com.example.diarycreation.presentation.viewmodel.action

import com.example.common.presentation.viewmodel.action.UIAction

sealed class DiaryCreationAction : UIAction {
    object NavigateBack : DiaryCreationAction()
    object RequestMediaAccessPermission : DiaryCreationAction()
    object OpenGallery : DiaryCreationAction()
    data class ShowSaveSuccessSnackBar(
        val message: String
    ) : DiaryCreationAction()
}