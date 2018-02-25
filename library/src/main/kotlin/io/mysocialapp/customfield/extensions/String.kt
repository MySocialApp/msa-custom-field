package io.mysocialapp.customfield.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
fun String?.toDate(): Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)

fun String?.convertToTheRightType(): Any? {
    if (this == null) {
        return null
    }

    if (this == "true" || this == "false") {
        return this.toBoolean()
    }

    if (this.startsWith("float|")) {
        return this.split("float|")[1].toFloat()
    }

    if (this.startsWith("long|")) {
        return this.split("long|")[1].toLong()
    }

    if (this.startsWith("tel|")) {
        return this.split("tel|")[1]
    }

    if (this.contains(".")) {
        try {
            return this.toDouble()
        } catch (e: NumberFormatException) {

        }
    }

    try {
        return this.toLong()
    } catch (e: NumberFormatException) {

    }

    return this
}