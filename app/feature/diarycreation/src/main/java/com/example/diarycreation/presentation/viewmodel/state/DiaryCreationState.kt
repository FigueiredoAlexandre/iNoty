package com.example.diarycreation.presentation.viewmodel.state

import com.example.common.presentation.viewmodel.state.UIState
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem
import com.example.util.emotions.Emotion

private const val FIRST_EMOTION_INDEX = 0
private const val ENTER_TITLE = "Digite um título"
private const val ENTER_DIARY_HERE = "Digite seu diário aqui :)"
private const val DIARY_TITLE = "Título do diário"
private const val MY_DIARY= "Meu diário"

internal data class DiaryCreationState(
    val diaryListOfImages: MutableList<DiaryCreationGalleryItem> = mutableListOf(
        DiaryCreationGalleryItem.AddImage
    ),
    val emotions: ArrayList<Emotion> = Emotion.listOfEmotions(),
    val currentEmotion: Emotion = Emotion.listOfEmotions()[FIRST_EMOTION_INDEX],
    val titleTextFieldHint: String = ENTER_TITLE,
    val contentTextFieldHint: String = ENTER_DIARY_HERE,
    val isLoading: Boolean = false
) : UIState {

    fun showLoading() = this.copy(isLoading = true)

    fun hideLoading() = this.copy(isLoading = false)
}