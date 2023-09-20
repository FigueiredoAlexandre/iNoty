package com.example.mylibrary.navigation

import android.content.Context
import android.content.Intent
import com.example.mylibrary.presentation.activity.DiaryListActivity
import com.example.navigation.featurenavigations.diarylist.DiaryListNavigation

internal class DiaryListNavigationImpl : DiaryListNavigation {

    override fun navigateToDiaryList(context: Context): Intent =
        DiaryListActivity.newInstance(context)
}