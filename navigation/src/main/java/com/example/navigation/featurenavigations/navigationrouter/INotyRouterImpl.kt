package com.example.navigation.featurenavigations.navigationrouter

import android.content.Context
import com.example.navigation.featurenavigations.diarycreation.DiaryCreationNavigation
import com.example.navigation.featurenavigations.diarylist.DiaryListNavigation
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class INotyRouterImpl : INotyRouter, KoinComponent {

    override fun toDiaryCreation(context: Context) {
        get<DiaryCreationNavigation>().navigateToDiaryCreation(context)
    }

    override fun toDiaryList(context: Context) {
        get<DiaryListNavigation>().navigateToDiaryList(context)
    }
}