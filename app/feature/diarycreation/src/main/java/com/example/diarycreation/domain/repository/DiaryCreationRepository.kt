package com.example.diarycreation.domain.repository

import com.example.diarycreation.domain.model.DiaryItem
import kotlinx.coroutines.flow.Flow

internal interface DiaryCreationRepository {

    fun saveDiary(diaryItem: DiaryItem): Flow<Unit>
}