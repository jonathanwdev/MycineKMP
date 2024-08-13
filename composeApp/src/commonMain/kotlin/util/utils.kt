package util

import presentation.screens.movie.components.DayItem
import java.time.LocalDate
import java.util.Calendar

fun convertMinutesToMovieTime(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}hrs ${remainingMinutes}mins"
}

private fun getDayOfWeek(day: Int, month: Int, year: Int): String {
    val date = LocalDate.of(year, month, day)
    val dayOfWeek = date.dayOfWeek.toString()
    return dayOfWeek
        .substring(0, 3)
        .lowercase()
        .replaceFirstChar { it.titlecase() }
}

fun createListOfDays(calendar: Calendar, callback: (List<DayItem>) -> Unit) {
    val totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    var list = listOf<DayItem>()
    repeat(totalDays) {
        val weekDay =
            getDayOfWeek(it + 1, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))
        list = list + DayItem(
            numberDay = it + 1,
            weekDay = weekDay
        )
    }
    callback(list)
}
