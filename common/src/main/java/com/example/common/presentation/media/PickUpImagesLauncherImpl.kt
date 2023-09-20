package com.example.common.presentation.media

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

class PickUpImagesLauncherImpl(
    private val intent: Intent,
    private val result: ActivityResultLauncher<Intent>
) : PickUpImagesLauncher {

    override fun launchGallery() {
        result.launch(intent)
    }
}