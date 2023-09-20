package com.example.diarycreation.domain.usecases

import com.example.diarycreation.domain.model.DiaryItem
import kotlinx.coroutines.flow.Flow

internal interface CreateDiaryUseCase {

    fun saveDiary(diary: DiaryItem): Flow<Unit>
}