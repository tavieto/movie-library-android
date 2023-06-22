package dev.tavieto.movielibrary.core.commons.extension

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar

fun String.toDate(format: String = "dd/MM/yyyy"): Date {
    val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
    return sdf.parse(this)
}

fun Date.formatToString(format: String = "yyyy-MM-dd"): String {
    val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
    return sdf.format(this)
}

fun Date.subtractYears(years: Int): Date = Calendar.getInstance().apply {
    time = this@subtractYears
    add(Calendar.YEAR, -years)
}.time
