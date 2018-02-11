package io.mysocialapp.customfield.extensions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * Created by evoxmusic on 30/12/16.
 */
fun Date?.toLocalDateTime() = this?.let { LocalDateTime.ofInstant(it.toInstant(), TimeZone.getTimeZone("UTC").toZoneId()) }

fun Date.toISO8601(): String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(this)

fun Date.getTimeDistanceInMinutes(from: Date = Date()): Int = Math.round((Math.abs(from.time - time) / 1000) / 60f)