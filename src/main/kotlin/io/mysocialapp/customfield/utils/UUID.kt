package io.mysocialapp.customfield.utils

import java.util.UUID

/**
 * Created by evoxmusic on 12/06/17.
 */
object UUID {

    fun generateLongId() = Math.abs(UUID.randomUUID().mostSignificantBits)

    fun generateStringId() = UUID.randomUUID().toString().replace("-", "")

    fun generateStringToken() = UUID.randomUUID().toString()

}