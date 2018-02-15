package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.mysocialapp.customfield.extensions.convertToTheRightType
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
data class FieldData(val fieldId: Long? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) val createdDate: Date? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) val updatedDate: Date? = null,
                     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) val value: Any? = null) {

    val fieldIdStr: String?
        get() = fieldId?.toString()

    val valueOut: Any?
        @JsonProperty("value", access = JsonProperty.Access.READ_ONLY)
        get() = if (value is List<*>) {
            value.map { it.toString().convertToTheRightType() }
        } else if (value is Map<*, *> && value["latitude"] != null && value["longitude"] != null) {
            value
        } else {
            value?.toString()?.convertToTheRightType()
        }

}