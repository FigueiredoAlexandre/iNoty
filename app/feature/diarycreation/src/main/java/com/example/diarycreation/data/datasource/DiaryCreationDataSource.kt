package com.example.diarycreation.data.datasource

import com.example.localstorage.entity.Diary
import kotlinx.coroutines.flow.Flow

internal interface DiaryCreationDataSource {

    fun saveDiary(diary: Diary): Flow<Unit>
}