package com.example.navigation.featurenavigations.diarycreation

import android.content.Context
import android.content.Intent

interface DiaryCreationNavigation {
    fun navigateToDiaryCreation(context: Context): Intent
}