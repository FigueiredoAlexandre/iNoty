package com.example.ui.expandablecard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.expandablecard.adapter.diffutils.ExpandableCardGalleryDiffUtil
import com.example.ui.expandablecard.adapter.model.GalleryItem
import com.example.ui.expandablecard.adapter.viewholder.AditionalImagesViewHolder
import com.example.ui.expandablecard.adapter.viewholder.ExpandableCardImagesViewHolder

private const val IMAGE_TYPE = 0
private const val ADDITIONAL_IMAGES_TYPE = 1

class ExpandableCardImagesAdapter : ListAdapter<GalleryItem, RecyclerView.ViewHolder>(
    ExpandableCardGalleryDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            IMAGE_TYPE -> ExpandableCardImagesViewHolder.build(parent)
            else -> AditionalImagesViewHolder.build(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExpandableCardImagesViewHolder -> {
                holder.onBind((getItem(position) as GalleryItem.ImageItem).imageUri)
            }
            is AditionalImagesViewHolder -> {
                holder.onBind((getItem(position) as GalleryItem.MoreImagesItem).amountOfImages)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GalleryItem.ImageItem -> IMAGE_TYPE
            else -> ADDITIONAL_IMAGES_TYPE
        }
    }
}