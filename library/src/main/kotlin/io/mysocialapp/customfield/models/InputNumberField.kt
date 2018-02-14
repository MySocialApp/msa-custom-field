package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
class InputNumberField(@JsonIgnore override var usageKey: String? = null,
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
        get() = FieldType.INPUT_NUMBER

    override fun validator(fieldData: FieldData) {
        try {
            fieldData.value?.toString()?.toDouble()
        } catch (e: NumberFormatException) {
            throw FieldFormatException("field value must be numeric")
        }
    }
}