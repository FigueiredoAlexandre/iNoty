package com.example.diarycreation.presentation.helper

import com.example.diarycreation.domain.model.DiaryCreationGalleryItem

internal class DiaryCreationGalleryHelper {

    var enteredTitle: String = ""
    var enteredContent: String = ""

    fun updateImagesList(
        oldList: MutableList<DiaryCreationGalleryItem>,
        newImage: DiaryCreationGalleryItem
    ): MutableList<DiaryCreationGalleryItem> {
        val newList: MutableList<DiaryCreationGalleryItem> = mutableListOf()

        newList.addAll(oldList)

        if (oldList.contains(newImage).not()) {
            newList.add(newImage)
        }

        return newList
    }
}