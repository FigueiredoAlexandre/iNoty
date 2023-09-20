package com.example.diarycreation.data.datasource

import com.example.localstorage.dao.DiaryDao
import com.example.localstorage.entity.Diary
import kotlinx.coroutines.flow.flow

internal class DiaryCreationDataSourceImpl(
    private val diaryDao: DiaryDao
) : DiaryCreationDataSource {

    override fun saveDiary(diary: Diary) = flow {
        emit(diaryDao.saveDiary(diary))
    }
}