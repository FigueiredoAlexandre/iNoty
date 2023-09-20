package com.example.mylibrary.domain.repository

import com.example.mylibrary.domain.model.DiaryListItem
import kotlinx.coroutines.flow.Flow

internal interface DiaryListRepository {

    fun getAllDiaries(): Flow<List<DiaryListItem>>
}