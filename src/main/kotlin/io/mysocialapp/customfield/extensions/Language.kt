package io.mysocialapp.customfield.extensions

import io.mysocialapp.customfield.models.Language

/**
 * Created by evoxmusic on 11/02/2018.
 */
fun String.toLanguage() = when (this.toUpperCase()) {
    "FR" -> Language.FR
    else -> Language.EN
}

fun Map<Language, String>.toStringKeysValues(): Map<String, String> = this.asSequence().fold(mutableMapOf()) { acc, v ->
    acc[v.key.toString()] = v.value
    acc
}