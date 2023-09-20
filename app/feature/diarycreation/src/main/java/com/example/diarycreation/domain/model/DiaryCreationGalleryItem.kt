package com.example.diarycreation.domain.model

internal sealed class DiaryCreationGalleryItem {

    data class Image(
        val imageReference: String
    ) : DiaryCreationGalleryItem()

    object AddImage : DiaryCreationGalleryItem()
}
