package com.space.core.util

import java.text.SimpleDateFormat
import java.util.*

object HelperMethods {

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun parseCurrentTimeToSimpleDateFormat(): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(getCurrentDateTime().time)
    }
}
