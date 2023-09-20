package com.example.diarycreation.data.repository

import com.example.common.data.mapper.Mapper
import com.example.diarycreation.data.datasource.DiaryCreationDataSource
import com.example.diarycreation.domain.model.DiaryItem
import com.example.diarycreation.domain.repository.DiaryCreationRepository
import com.example.localstorage.entity.Diary
import kotlinx.coroutines.flow.Flow

internal class DiaryCreationRepositoryImpl(
    private val diaryCreationMapper: Mapper<DiaryItem, Diary>,
    private val diaryCreationSource: DiaryCreationDataSource
) : DiaryCreationRepository {

    override fun saveDiary(diaryItem: DiaryItem): Flow<Unit> {
        return diaryCreationSource.saveDiary(
            diaryCreationMapper.map(diaryItem)
        )
    }
}