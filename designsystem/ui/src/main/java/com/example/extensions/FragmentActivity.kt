package com.example.extensions

import androidx.fragment.app.FragmentActivity
import com.example.ui.progress.INotyProgressDialog

private const val INOTY_PROGRESS_TAG = "iNotyProgress"

fun FragmentActivity.showINotyProgressDialog() {
    this.supportFragmentManager.beginTransaction()
        .add(INotyProgressDialog.newInstance(), INOTY_PROGRESS_TAG)
        .commitAllowingStateLoss()
}

fun FragmentActivity.hideINotyProgressDialog() {
    val fragment = this.supportFragmentManager.findFragmentByTag(INOTY_PROGRESS_TAG)
    if (fragment is INotyProgressDialog) fragment.dismissAllowingStateLoss()
}