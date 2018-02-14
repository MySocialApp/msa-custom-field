package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.mysocialapp.customfield.extensions.toStringKeysValues
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
open class InputTextField(@JsonIgnore override var usageKey: String? = null,
                          @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var id: Long? = null,
                          override var enabled: Boolean? = null,
                          override var accessControl: AccessControl? = null,
                          @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var createdDate: Date? = null,
                          @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var updatedDate: Date? = null,
                          override var labels: Map<Language, String>? = null,
                          override var descriptions: Map<Language, String>? = null,
                          override var placeholders: Map<Language, String>? = null) : Field {

    override val fieldType: FieldType
        get() = FieldType.INPUT_TEXT


    override fun checkValidity() {
        if (labels == null || labels?.isEmpty() == true) {
            throw MissingMandatoryFieldException("you must add at least one 'name' field")
        }
    }

    override fun validator(fieldData: FieldData) {
        if (fieldData.value !is String) {
            throw FieldFormatException("field value must be string")
        }
    }

    override val customField: CustomField
        @JsonIgnore
        get() = CustomField(
                usageKey,
                id,
                createdDate,
                updatedDate,
                fieldType,
                enabled,
                accessControl,
                labels?.toStringKeysValues(),
                descriptions?.toStringKeysValues(),
                placeholders?.toStringKeysValues(),
                null,
                null,
                null,
                null
        )

}