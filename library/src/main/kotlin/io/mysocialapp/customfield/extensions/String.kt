package io.mysocialapp.customfield.extensions

import java.text.SimpleDateFormat

/**
 * Created by evoxmusic on 11/02/2018.
 */
fun String.toDate() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)