package com.example.mylibrary.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.presentation.viewmodel.INotyViewModel
import com.example.mylibrary.domain.model.DiaryListItem
import com.example.mylibrary.domain.usecase.GetListOfDiariesUseCase
import com.example.mylibrary.presentation.viewmodel.action.DiaryListAction
import com.example.mylibrary.presentation.viewmodel.state.DiaryListState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

internal class DiaryListViewModel(
    private val getDiariesUseCase: GetListOfDiariesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : INotyViewModel<DiaryListState, DiaryListAction>(DiaryListState()) {

    fun onDiaryListResumed() {
        viewModelScope.launch {
            getDiariesUseCase.getDiaries()
                .flowOn(dispatcher)
                .onStart { setState { it.showLoading() } }
                .onCompletion { setState { it.hideLoading() } }
                .catch {

                }
                .collect { retrievedDiaries ->
                    buildSuccessState(retrievedDiaries)
                }
        }
    }

    fun onDiaryShowImagesClicked(diaryId: Int) {
        setState { state ->
            state.copy(diaries = updateListOfDiaries(state.diaries, diaryId))
        }
    }

    private fun updateListOfDiaries(
        oldList: List<DiaryListItem>,
        modifiedDiaryId: Int
    ): List<DiaryListItem> {
        val newList = oldList.map {
            it.mapDiaryItem(modifiedDiaryId)
        }
        return newList
    }

    private fun DiaryListItem.mapDiaryItem(modifiedDiaryId: Int): DiaryListItem {
        val currentDiaryItem = this
        return when {
            currentDiaryItem is DiaryListItem.Header -> currentDiaryItem
            currentDiaryItem is DiaryListItem.DiaryContent && currentDiaryItem.id == modifiedDiaryId -> {
                currentDiaryItem.also { it.changeExpandedStatus() }
            }

            else -> currentDiaryItem
        }
    }

    private fun buildSuccessState(incomingDiaries: List<DiaryListItem>) {
        setState { state ->
            state.successState(
                retrieveNewListOfDiaries(state.diaries, incomingDiaries)
            )
        }
    }

    private fun retrieveNewListOfDiaries(
        oldList: List<DiaryListItem>,
        newList: List<DiaryListItem>
    ): List<DiaryListItem> {
        val finalList: MutableList<DiaryListItem> = mutableListOf<DiaryListItem>().also {
            it.addAll(oldList)
        }

        newList.forEach { diaryListItem ->
            if (oldList.contains(diaryListItem).not()) {
                finalList.add(diaryListItem)
            }
        }

        return finalList
    }

    fun onWriteNewDiaryButtonClicked() {
        sendAction { DiaryListAction.NavigateToDiaryCreation }
    }

    fun onScreenRefreshed() {
        endRefreshing()
    }

    private fun endRefreshing() {
        sendAction { DiaryListAction.EndRefreshing }
    }
}