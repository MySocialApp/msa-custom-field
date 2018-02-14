package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
data class FieldData(val fieldId: Long? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) val createdDate: Date? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) val updatedDate: Date? = null,
                     val value: Any? = null,
                     val values: List<Any>? = null) {

    val fieldIdStr: String?
        get() = fieldId?.toString()

}