package com.example.diarycreation.presentation.navigation

import android.content.Context
import android.content.Intent
import com.example.diarycreation.presentation.DiaryCreationActivity
import com.example.navigation.featurenavigations.diarycreation.DiaryCreationNavigation

internal class DiaryCreationNavigationImpl : DiaryCreationNavigation {


    override fun navigateToDiaryCreation(context: Context): Intent =
        DiaryCreationActivity.newInstance(context)
}