package com.kalpv.turtlemintdemo.util

import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter

fun String.toFormattedDateString(format: String = "MM-dd-yyyy"): String {
    val instant: Instant = Instant.parse(this)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())
    return localDateTime.format(DateTimeFormatter.ofPattern(format))
}