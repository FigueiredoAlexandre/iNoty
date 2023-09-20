package com.example.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.localstorage.converters.Converters
import com.example.localstorage.dao.DiaryDao
import com.example.localstorage.entity.Diary

@Database(version = 1, entities = [Diary::class])
@TypeConverters(Converters::class)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun getDao(): DiaryDao
}