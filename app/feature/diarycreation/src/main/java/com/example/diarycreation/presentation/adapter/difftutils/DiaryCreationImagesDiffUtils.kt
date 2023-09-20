package com.example.diarycreation.presentation.adapter.difftutils

import androidx.recyclerview.widget.DiffUtil
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem

internal class DiaryCreationImagesDiffUtils : DiffUtil.ItemCallback<DiaryCreationGalleryItem>() {

    override fun areItemsTheSame(
        oldItem: DiaryCreationGalleryItem,
        newItem: DiaryCreationGalleryItem
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: DiaryCreationGalleryItem,
        newItem: DiaryCreationGalleryItem
    ): Boolean = oldItem == newItem
}