package com.example.common.presentation.extensions

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}