package com.example.ui.expandablecard.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.ui.expandablecard.adapter.model.GalleryItem

class ExpandableCardGalleryDiffUtil : DiffUtil.ItemCallback<GalleryItem>() {

    override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
        (areImagesUriTheSame(oldItem, newItem) || (areAdditionalImageItemsTheSame(
            oldItem,
            newItem
        )))

    private fun areImagesUriTheSame(
        oldItem: GalleryItem, newItem: GalleryItem
    ): Boolean {
        return if (oldItem is GalleryItem.ImageItem && newItem is GalleryItem.ImageItem) {
            (oldItem.imageUri == newItem.imageUri)
        } else {
            false
        }
    }

    private fun areAdditionalImageItemsTheSame(
        oldItem: GalleryItem, newItem: GalleryItem
    ): Boolean {
        return if (oldItem is GalleryItem.MoreImagesItem && newItem is GalleryItem.MoreImagesItem) {
            (oldItem.amountOfImages == newItem.amountOfImages)
        } else {
            false
        }
    }
}