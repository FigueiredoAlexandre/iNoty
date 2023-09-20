package com.example.common.presentation.extensions

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale
import java.util.Date

private const val TIME_FORMAT = "dd/MM/yyyy"
private const val DATE_SEPARATOR = "/"
private const val MONTH_INDEX = 1
private const val NON_ZERO_NUMBER_INDEX = 1
private const val DAY_INDEX = 0
private const val YEAR_INDEX = 2

fun String.retrieveMonthName(): String {
    var month = this.split(DATE_SEPARATOR)[MONTH_INDEX]
    if (month.hasTwoNumbersAndStarsWithZero()) {
        month = month.substring(NON_ZERO_NUMBER_INDEX)
    }
    return DateFormatSymbols()
        .months[month.toInt() - 1]
        .toString()
        .replaceFirstChar { char ->
            char.uppercaseChar()
        }
}

fun String.retrieveDayNumber(): String {
    return this.split(DATE_SEPARATOR)[DAY_INDEX]
}

fun String.retrieveYear(): String =
    this.split(DATE_SEPARATOR)[YEAR_INDEX]

private fun String.hasTwoNumbersAndStarsWithZero() =
    (this.startsWith("0") && this.length > 1)


fun String.retrieveDayOfTheWeekName(): String {
    val dayMonthAndYear = this.split(DATE_SEPARATOR)
    return LocalDate.of(
        dayMonthAndYear[2].toInt(),
        dayMonthAndYear[1].removeFirstZero().toInt(),
        dayMonthAndYear[0].toInt()
    ).dayOfWeek.toString().toPortugueseAbreviation()
}

private fun String.toPortugueseAbreviation(): String {
    return when (this) {
        "MONDAY" -> "Segunda"
        "TUESDAY" -> "Terça"
        "WEDNESDAY" -> "Quarta"
        "THURSDAY" -> "Quinta"
        "FRIDAY" -> "Sexta"
        "SATURDAY" -> "Sábado"
        else -> "Domingo"
    }
}

private fun String.removeFirstZero(): String {
    return if (this.hasTwoNumbersAndStarsWithZero()) {
        this.substring(NON_ZERO_NUMBER_INDEX)
    } else {
        this
    }
}

fun getCurrentDateTime(): String {
    return Calendar
        .getInstance()
        .time
        .toFormattedString(TIME_FORMAT)
}

fun Date.toFormattedString(format: String, locale: Locale = Locale("pt", "BR")): String {
    return SimpleDateFormat(format, locale).format(this)
}