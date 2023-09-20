package com.example.localstorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localstorage.entity.Diary

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary ")
    fun getAllDiaries(): List<Diary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDiary(diary: Diary)
}