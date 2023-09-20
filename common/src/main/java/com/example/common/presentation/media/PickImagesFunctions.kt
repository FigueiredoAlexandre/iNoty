package com.example.common.presentation.media

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.openGallery(
    callback: (imageUri: Uri?) -> Unit
): PickUpImagesLauncher {
    return PickUpImagesLauncherImpl(
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
        galleryResultLauncher(callback)
    )
}

internal fun FragmentActivity.galleryResultLauncher(
    callback: (imageUri: Uri?) -> Unit
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            callback(result.data?.data)
        }
    }
}