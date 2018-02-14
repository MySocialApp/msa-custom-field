package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.mysocialapp.customfield.extensions.toDate
import java.text.ParseException
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
class InputDateField(@JsonIgnore override var usageKey: String? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var id: Long? = null,
                     override var enabled: Boolean? = null,
                     override var accessControl: AccessControl? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var createdDate: Date? = null,
                     @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var updatedDate: Date? = null,
                     override var labels: Map<Language, String>? = null,
                     override var descriptions: Map<Language, String>? = null,
                     override var placeholders: Map<Language, String>? = null
) : InputTextField(usageKey, id, enabled, accessControl, createdDate, updatedDate, labels, descriptions, placeholders) {

    override val fieldType: FieldType
        get() = FieldType.INPUT_DATE

    override fun validator(fieldData: FieldData) {
        try {
            fieldData.value?.toString()?.toDate()
        } catch (e: ParseException) {
            throw FieldFormatException("field value must be ISO8601 date format")
        }
    }
}