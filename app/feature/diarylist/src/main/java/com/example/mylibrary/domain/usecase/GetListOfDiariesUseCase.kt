package com.example.mylibrary.domain.usecase

import com.example.mylibrary.domain.model.DiaryListItem
import kotlinx.coroutines.flow.Flow

internal interface GetListOfDiariesUseCase {

    fun getDiaries(): Flow<List<DiaryListItem>>
}