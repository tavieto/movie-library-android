package dev.tavieto.movielibrary.core.util.enums

import com.raptor.sports.core.R.string.date_week_day_friday
import com.raptor.sports.core.R.string.date_week_day_monday
import com.raptor.sports.core.R.string.date_week_day_saturday
import com.raptor.sports.core.R.string.date_week_day_sunday
import com.raptor.sports.core.R.string.date_week_day_thursday
import com.raptor.sports.core.R.string.date_week_day_tuesday
import com.raptor.sports.core.R.string.date_week_day_wednesday

enum class WeekDay(val id: String, val resourceId: Int) {
    SUNDAY(id = "sunday", resourceId = date_week_day_sunday),
    MONDAY(id = "monday", resourceId = date_week_day_monday),
    TUESDAY(id = "tuesday", resourceId = date_week_day_tuesday),
    THURSDAY(id = "thursday", resourceId = date_week_day_thursday),
    WEDNESDAY(id = "wednesday", resourceId = date_week_day_wednesday),
    FRIDAY(id = "friday", resourceId = date_week_day_friday),
    SATURDAY(id = "saturday", resourceId = date_week_day_saturday);

    companion object {
        fun getById(id: String): WeekDay = values().firstOrNull { id == it.id } ?: SUNDAY
    }
}
