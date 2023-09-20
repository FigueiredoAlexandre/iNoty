package com.example.ui.expandablecard.adapter.model

import android.net.Uri

sealed class GalleryItem {

    data class ImageItem(
        val imageUri: Uri
    ) : GalleryItem()

    data class MoreImagesItem(
        val amountOfImages: Int
    ) : GalleryItem()
}
