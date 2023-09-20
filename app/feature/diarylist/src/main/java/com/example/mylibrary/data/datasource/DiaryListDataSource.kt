package com.example.mylibrary.data.datasource

import com.example.localstorage.entity.Diary

internal interface DiaryListDataSource {

    fun getAllDiaries(): List<Diary>
}