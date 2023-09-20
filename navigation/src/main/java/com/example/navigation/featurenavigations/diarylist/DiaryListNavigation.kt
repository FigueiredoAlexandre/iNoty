package com.example.navigation.featurenavigations.diarylist

import android.content.Context
import android.content.Intent

interface DiaryListNavigation {

    fun navigateToDiaryList(context: Context): Intent
}