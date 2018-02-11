package io.mysocialapp.customfield.models

import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
data class FieldData(val fieldId: Long? = null,
                     val createdDate: Date? = null,
                     val updatedDate: Date? = null,
                     val value: Any? = null,
                     val values: List<Any>? = null) {

    val fieldIdStr: String?
        get() = fieldId?.toString()

}