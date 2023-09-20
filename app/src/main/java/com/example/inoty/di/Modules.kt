package com.example.inoty.di

import com.example.navigation.featurenavigations.navigationrouter.INotyRouter
import com.example.navigation.featurenavigations.navigationrouter.INotyRouterImpl
import org.koin.dsl.module

private const val DATABASE_NAME = "diary_database"

val routerModule = module {
    factory<INotyRouter> { INotyRouterImpl() }
}

/* val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            DiaryDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<DiaryDao> {
        get<DiaryDatabase>().getDao()
    }
} */