package dev.tavieto.movielibrary.core.commons.util

import androidx.annotation.RequiresApi
import com.raptor.sports.commons.extension.Empty
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.OffsetTime
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale.getDefault
import java.util.concurrent.TimeUnit

const val ANDROID_API_O = 26

@RequiresApi(ANDROID_API_O)
object DateProvider {
    var timeOffsetTime: Long = TimeUnit.SECONDS.toHours(
        OffsetTime.now().offset.totalSeconds.toLong()
    )

    private fun getInstantTime(): OffsetDateTime = Instant.now().atOffset(ZoneOffset.UTC)

    val now
        get() = getInstantTime().toEpochSecond()

    fun getTodayFinalSeconds(): Long = getInstantTime().atEndDay().toEpochSecond()

    fun isToday(dayName: String, dayNumber: Int): Boolean {
        val now = getInstantTime()
        val equalsName = now.dayOfWeek.name.uppercase() == dayName.uppercase()
        val equalsNumber = now.dayOfMonth == dayNumber
        return equalsName && equalsNumber
    }
}

object DateProviderCompact {
    private val now = Date()

    fun isToday(date: String): Boolean {
        return now.setPattern(DATE_PATTERN) == date.convertPattern(target = DATE_PATTERN)
    }
}

fun Long.toSeconds(): Long {
    return if (this.toString().length >= 11) {
        TimeUnit.MILLISECONDS.toSeconds(this)
    } else this
}

@RequiresApi(ANDROID_API_O)
fun Long.toDate(): Date {
    return Date.from(
        Instant.ofEpochSecond(toSeconds()).atOffset(ZoneOffset.UTC).toInstant()
    )
}

fun Date.toPattern(): String {
    return try {
        val dateFormatter = SimpleDateFormat(DATE_TIME_PATTERN, getDefault())
        dateFormatter.format(this)
    } catch (e: Throwable) {
        String()
    }
}

fun Date.setPattern(pattern: String = "MM/dd/yyyy, KK:mm a"): String {
    val dateFormatter = SimpleDateFormat(pattern, getDefault())
    return dateFormatter.format(this)
}

fun String.toDate(pattern: String = DATE_TIME_PATTERN): Date {
    return SimpleDateFormat(pattern, getDefault()).parse(this)
}

fun String.convertPattern(
    current: String = DATE_TIME_PATTERN,
    target: String = MONTH_DAY_PATTERN
): String {
    return this.toDateOrNull(current)?.setPattern(target) ?: String.Empty
}

@RequiresApi(ANDROID_API_O)
fun String.extractDay(pattern: String = DATE_PATTERN): Int {
    val date = this.toDateOrNull(pattern)
    return date?.toInstant()?.atOffset(ZoneOffset.UTC)?.dayOfMonth ?: -1
}

fun String.toDateOrNull(pattern: String = DATE_TIME_PATTERN): Date? {
    return try {
        try {
            toDate(pattern)
        } catch (e: Throwable) {
            substring(0..(pattern.length.minus(1))).toDate()
        }
    } catch (e: Throwable) {
        null
    }
}

@RequiresApi(ANDROID_API_O)
fun Long.toPattern(): String {
    return toDate().toPattern()
}

@RequiresApi(ANDROID_API_O)
internal fun OffsetDateTime.atStartDay(): OffsetDateTime {
    return minusHours(hour.toLong())
        .minusMinutes(minute.toLong())
        .minusSeconds(second.toLong())
}

@RequiresApi(ANDROID_API_O)
internal fun OffsetDateTime.atEndDay(): OffsetDateTime {
    val finalHour = 23L
    val finalMinute = 59L
    val finalSecond = 59L

    return plusHours(finalHour - hour)
        .plusMinutes(finalMinute - minute)
        .plusSeconds(finalSecond - second)
}

private const val DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
private const val DATE_PATTERN = "yyyy-MM-dd"
private const val MONTH_DAY_PATTERN = "MMM d"
const val DAY_PATTERN = "EEE, d"
const val HOUR_PATTERN = "h:mm a"