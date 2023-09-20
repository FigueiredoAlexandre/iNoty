package com.example.diarycreation.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.presentation.extensions.getCurrentDateTime
import com.example.common.presentation.permissions.PermissionStatus
import com.example.common.presentation.viewmodel.INotyViewModel
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem
import com.example.diarycreation.domain.model.DiaryItem
import com.example.diarycreation.domain.usecases.CreateDiaryUseCase
import com.example.diarycreation.presentation.helper.DiaryCreationGalleryHelper
import com.example.diarycreation.presentation.viewmodel.action.DiaryCreationAction
import com.example.diarycreation.presentation.viewmodel.state.DiaryCreationState
import com.example.util.emotions.Emotion
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

private const val DIARY_TITLE = "Título do diário"
private const val MY_DIARY = "Meu diário: "
private const val ENTER_TITLE = "Digite um título"
private const val ENTER_DIARY_HERE = "Digite seu diário aqui :)"
private const val ZERO_VALUE = 0
private const val SAVE_SUCCESS = "Diário salvo! :)"

internal class DiaryCreationViewModel(
    private val createDiaryUseCase: CreateDiaryUseCase,
    private val helper: DiaryCreationGalleryHelper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : INotyViewModel<DiaryCreationState, DiaryCreationAction>(DiaryCreationState()) {

    fun onAddImageClicked() {
        sendAction { DiaryCreationAction.RequestMediaAccessPermission }
    }

    fun onPermissionVerified(status: PermissionStatus) {
        when (status) {
            PermissionStatus.Granted -> sendAction { DiaryCreationAction.OpenGallery }
            else -> Unit
        }
    }

    fun onGalleryImageChosen(
        imageUri: String
    ) {
        setState { state ->
            state.copy(
                diaryListOfImages = helper.updateImagesList(
                    state.diaryListOfImages,
                    DiaryCreationGalleryItem.Image(imageUri)
                )
            )
        }
    }

    fun onToolbarBackIconClicked() {
        sendAction { DiaryCreationAction.NavigateBack }
    }

    fun onNextEmotionArrowClicked() {
        setState { state ->
            state.copy(
                currentEmotion = state.emotions
                    .getOrNull(
                        state.emotions.indexOfNextEmotionFrom(state.currentEmotion)
                    )?: state.emotions.first()
            )
        }
    }

    private fun ArrayList<Emotion>.indexOfNextEmotionFrom(currentEmotion: Emotion): Int {
        return indexOf(currentEmotion) + 1
    }

    fun onPreviousEmotionArrowClicked() {
        setState { state ->
            state.copy(
                currentEmotion = state.emotions
                    .getOrNull(
                        state.emotions.indexOfPreviousEmotionFrom(state.currentEmotion)
                    )?: state.emotions.last()
            )
        }
    }

    private fun ArrayList<Emotion>.indexOfPreviousEmotionFrom(currentEmotion: Emotion): Int {
        return indexOf(currentEmotion) - 1
    }

    fun onTitleEntered(title: String) {
        helper.enteredTitle = title
        if (title.isNotEmpty()) {
            setState { state -> state.copy(titleTextFieldHint = DIARY_TITLE) }
        } else {
            setState { state -> state.copy(titleTextFieldHint = ENTER_TITLE) }
        }
    }

    fun onDiaryContentEntered(content: String) {
        helper.enteredContent = content
        if (content.isNotEmpty()) {
            setState { state -> state.copy(contentTextFieldHint = MY_DIARY) }
        } else {
            setState { state -> state.copy(contentTextFieldHint = ENTER_DIARY_HERE) }
        }
    }

    fun onSaveDiaryButtonClicked() {
        viewModelScope.launch {
            createDiaryUseCase.saveDiary(
                DiaryItem(
                    title = helper.enteredTitle,
                    content = helper.enteredContent,
                    emoji = state.value?.currentEmotion?.emoji ?: ZERO_VALUE,
                    images = state.value?.diaryListOfImages ?: emptyList(),
                    time = getCurrentDateTime()
                )
            )
                .flowOn(dispatcher)
                .onStart { setState { it.showLoading() } }
                .onCompletion { setState { it.hideLoading() } }
                .catch {  }
                .collect {
                   sendAction { DiaryCreationAction.ShowSaveSuccessSnackBar(SAVE_SUCCESS) }
                }
        }
    }

    fun onSuccessSnackbarDismissed() {
        sendAction { DiaryCreationAction.NavigateBack }
    }
}