package com.example.mylibrary.domain.usecase

import com.example.mylibrary.domain.model.DiaryListItem
import com.example.mylibrary.domain.repository.DiaryListRepository
import kotlinx.coroutines.flow.Flow

internal class GetListOfDiariesUseCaseImpl(
    private val repository: DiaryListRepository
) : GetListOfDiariesUseCase {

    override fun getDiaries(): Flow<List<DiaryListItem>> =
        repository.getAllDiaries()
}