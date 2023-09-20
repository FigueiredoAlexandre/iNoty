package com.example.mylibrary.data.mapper

import com.example.common.data.mapper.Mapper
import com.example.common.presentation.extensions.retrieveDayNumber
import com.example.common.presentation.extensions.retrieveDayOfTheWeekName
import com.example.common.presentation.extensions.retrieveMonthName
import com.example.common.presentation.extensions.retrieveYear
import com.example.localstorage.entity.Diary
import com.example.mylibrary.domain.model.DiaryListItem

private const val EMPTY_DEFAULT = ""

internal class DiaryListMapper : Mapper<List<Diary>, List<DiaryListItem>> {

    private var currentDate: String = EMPTY_DEFAULT
    private val listOfDiaries: MutableList<DiaryListItem> = mutableListOf()

    override fun map(source: List<Diary>): List<DiaryListItem> {
        source.forEach { diary ->
            mapHeader(diary)
            mapDiaryContent(diary)
        }
        return listOfDiaries
    }

    private fun mapHeader(diary: Diary) {
        if (diary.date.isNewDate()) {
            updateCurrentDate(diary.date)
            listOfDiaries.add(
                DiaryListItem.Header(
                    dayNumber = diary.date.retrieveDayNumber(),
                    weekDay = diary.date.retrieveDayOfTheWeekName(),
                    month = diary.date.retrieveMonthName(),
                    year = diary.date.retrieveYear()
                )
            )
        }
    }

    private fun mapDiaryContent(diary: Diary) {
        listOfDiaries.add(
            DiaryListItem.DiaryContent(
                emojiReference = diary.emoji,
                title = diary.title,
                body = diary.content,
                images = diary.images,
                id = diary.id
            )
        )
    }

    private fun String.isNewDate() =
        currentDate != this

    private fun updateCurrentDate(date: String) {
        currentDate = date
    }
}