package com.example.mylibrary.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.mylibrary.domain.model.DiaryListItem

internal class DiaryListDiffUtil : DiffUtil.ItemCallback<DiaryListItem>() {

    override fun areItemsTheSame(oldItem: DiaryListItem, newItem: DiaryListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DiaryListItem, newItem: DiaryListItem): Boolean {
        return (areHeadersTheSame(oldItem, newItem) || areDiariesContentTheSame(oldItem, newItem))
    }

    private fun areHeadersTheSame(
        oldItem: DiaryListItem,
        newItem: DiaryListItem
    ): Boolean {
        return if (oldItem is DiaryListItem.Header && newItem is DiaryListItem.Header) {
            (oldItem == newItem)
        } else {
            false
        }
    }

    private fun areDiariesContentTheSame(
        oldItem: DiaryListItem,
        newItem: DiaryListItem
    ): Boolean {
        return if (oldItem is DiaryListItem.DiaryContent && newItem is DiaryListItem.DiaryContent) {
            attributesAreEqual(oldItem, newItem)
        } else {
            false
        }
    }

    private fun attributesAreEqual(
        oldItem: DiaryListItem.DiaryContent,
        newItem: DiaryListItem.DiaryContent
    ): Boolean {
        return (imagesAreEqual(oldItem, newItem) &&
                idsAreEqual(oldItem, newItem) &&
                bodiesAreEqual(oldItem, newItem) &&
                titlesAreEqual(oldItem, newItem))
    }

    private fun imagesAreEqual(
        oldItem: DiaryListItem.DiaryContent,
        newItem: DiaryListItem.DiaryContent
    ): Boolean = oldItem.images == newItem.images

    private fun idsAreEqual(
        oldItem: DiaryListItem.DiaryContent,
        newItem: DiaryListItem.DiaryContent
    ): Boolean = oldItem.id == newItem.id

    private fun bodiesAreEqual(
        oldItem: DiaryListItem.DiaryContent,
        newItem: DiaryListItem.DiaryContent
    ): Boolean = oldItem.body == newItem.body

    private fun titlesAreEqual(
        oldItem: DiaryListItem.DiaryContent,
        newItem: DiaryListItem.DiaryContent
    ): Boolean = oldItem.title == newItem.title
}