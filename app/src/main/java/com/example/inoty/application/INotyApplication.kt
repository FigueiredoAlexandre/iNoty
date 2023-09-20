package com.example.inoty.application

import android.app.Application
import com.example.diarycreation.di.diaryCreationModule
import com.example.diarycreation.di.diaryCreationNavigationModule
import com.example.inoty.di.routerModule
import com.example.localstorage.di.roomDatabaseModule
import com.example.mylibrary.di.diaryListModule
import com.example.mylibrary.di.diaryListNavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class INotyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@INotyApplication)
            modules(
                diaryCreationNavigationModule,
                diaryListNavigationModule,
                routerModule,
                roomDatabaseModule,
                diaryCreationModule,
                diaryListModule
            )
        }
    }
}