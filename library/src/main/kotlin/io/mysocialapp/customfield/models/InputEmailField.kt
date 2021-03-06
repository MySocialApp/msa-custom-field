package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.validator.routines.EmailValidator
import java.util.*

/**
 * Created by evoxmusic on 24/02/2018.
 */
class InputEmailField(@JsonIgnore override var usageKey: String? = null,
                      override var id: Long? = null,
                      override var enabled: Boolean? = null,
                      override var important: Boolean? = null,
                      override var accessControl: AccessControl? = null,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var createdDate: Date? = null,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) override var updatedDate: Date? = null,
                      override var labels: Map<Language, String>? = null,
                      override var descriptions: Map<Language, String>? = null,
                      override var placeholders: Map<Language, String>? = null,
                      override var values: Map<Language, List<String>>? = null,
                      override var defaultValue: Int? = null,
                      override var position: Int? = null
) : InputTextField(usageKey, id, enabled, important, accessControl, createdDate, updatedDate, labels, descriptions,
        placeholders, values, defaultValue, position) {

    override val fieldType: FieldType
        get() = FieldType.INPUT_EMAIL

    override fun validator(fieldData: FieldData) {
        val email = fieldData.value?.toString()?.toLowerCase()
        if (email?.isNotBlank() == true && !EmailValidator.getInstance().isValid(email)) {
            throw FieldFormatException("field value must be valid Email format")
        }
    }
}