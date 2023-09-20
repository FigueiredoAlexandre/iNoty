package com.example.localstorage.di

import androidx.room.Room
import com.example.localstorage.dao.DiaryDao
import com.example.localstorage.database.DiaryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "diary_database"

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            DiaryDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<DiaryDao> {
        get<DiaryDatabase>().getDao()
    }
}