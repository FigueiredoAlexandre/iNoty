package com.example.diarycreation.data.mapper

import com.example.common.data.mapper.Mapper
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem
import com.example.diarycreation.domain.model.DiaryItem
import com.example.localstorage.entity.Diary

internal class DiaryCreationMapper : Mapper<DiaryItem, Diary> {

    override fun map(source: DiaryItem): Diary {
       return Diary(
           title = source.title,
           content = source.content,
           emoji = source.emoji,
           images = convertImages(source.images),
           date = source.time
       )
    }

    private fun convertImages(
        sourceImagesItems: List<DiaryCreationGalleryItem>
    ): List<String> {
        return sourceImagesItems.filter {
            (it is DiaryCreationGalleryItem.Image)
        }.getImagesReferencesStrings()
    }

    private fun List<DiaryCreationGalleryItem>.getImagesReferencesStrings(): List<String> {
        return this.map {
            (it as DiaryCreationGalleryItem.Image).imageReference
        }
    }
}