package com.example.diarycreation.domain.model

internal data class DiaryItem(
    val title: String,
    val content: String,
    val emoji: Int,
    val time: String,
    val images: List<DiaryCreationGalleryItem> = listOf(DiaryCreationGalleryItem.AddImage)
)