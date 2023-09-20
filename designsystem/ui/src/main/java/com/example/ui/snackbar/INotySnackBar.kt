package com.example.ui.snackbar

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import com.example.ui.databinding.InotySnackbarLayoutBinding
import com.google.android.material.snackbar.Snackbar

private const val DURATION_DEFAULT = 3000

class INotySnackBar {

    class Builder internal constructor(view: View, val text: String, duration: Int) {

        private val context: Context = view.context
        private val snackbar: Snackbar = Snackbar.make(view, "", duration)
        private val snackBarView: ViewGroup = snackbar.view as (Snackbar.SnackbarLayout)
        private val customSnackBarView = InotySnackbarLayoutBinding
            .inflate(LayoutInflater.from(context))
        private var onDismiss: () -> Unit = {}
        private val onDismissListener = Runnable {
            onDismiss.invoke()
        }

        init {
            configView()
        }

        private fun configView() {
            with(snackBarView) {
                background = android.R.color.transparent.toDrawable()
                addView(customSnackBarView.root)
            }
            customSnackBarView.snackBarText.text = text
        }

        fun setDuration(duration: Int): INotySnackBar.Builder {
            snackbar.duration = duration
            return this
        }

        fun onSnackBarDismissed(callback: () -> Unit): INotySnackBar.Builder {
            onDismiss = callback
            return this
        }

        fun show() {
            snackbar.show()
            Handler(Looper.getMainLooper()).postDelayed(
                onDismissListener,
                snackbar.duration.toLong()
            )
        }
    }

    companion object {

        @JvmStatic
        fun make(view: View,  text: String, duration: Int = DURATION_DEFAULT) =
            Builder(view, text, duration)
    }
}