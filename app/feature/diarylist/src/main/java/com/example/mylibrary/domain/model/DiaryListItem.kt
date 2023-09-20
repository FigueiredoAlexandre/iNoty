package com.example.mylibrary.domain.model

internal sealed class DiaryListItem {

    data class Header(
        val dayNumber: String,
        val weekDay: String,
        val month: String,
        val year: String
    ) : DiaryListItem()

    data class DiaryContent(
        val id: Int,
        val emojiReference: Int,
        val title: String,
        val body: String,
        val images: List<String>,
        var isExpanded: Boolean = false
    ) : DiaryListItem() {

        fun changeExpandedStatus() {
            isExpanded = !isExpanded
        }
    }
}