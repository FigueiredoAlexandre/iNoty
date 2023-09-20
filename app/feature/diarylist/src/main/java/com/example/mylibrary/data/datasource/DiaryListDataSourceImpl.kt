package com.example.mylibrary.data.datasource

import com.example.localstorage.dao.DiaryDao
import com.example.localstorage.entity.Diary

internal class DiaryListDataSourceImpl(
    private val diaryDao: DiaryDao
) : DiaryListDataSource {

    override fun getAllDiaries(): List<Diary> =
        diaryDao.getAllDiaries()
}