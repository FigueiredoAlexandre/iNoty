package com.example.mylibrary.data.repository

import com.example.common.data.mapper.Mapper
import com.example.localstorage.entity.Diary
import com.example.mylibrary.data.datasource.DiaryListDataSource
import com.example.mylibrary.domain.model.DiaryListItem
import com.example.mylibrary.domain.repository.DiaryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class DiaryListRepositoryImpl(
    private val dataSource: DiaryListDataSource,
    private val mapper: Mapper<List<Diary>, List<DiaryListItem>>
) : DiaryListRepository {

    override fun getAllDiaries(): Flow<List<DiaryListItem>> = flow {
        emit(
            mapper.map(
                dataSource.getAllDiaries()
            )
        )
    }
}