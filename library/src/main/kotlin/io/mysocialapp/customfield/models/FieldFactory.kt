package io.mysocialapp.customfield.models

import io.mysocialapp.customfield.extensions.toStringByLanguage

/**
 * Created by evoxmusic on 11/02/2018.
 */
object FieldFactory {

    fun from(customField: CustomField): Field? = when (customField.type) {
        FieldType.INPUT_TEXT -> completeField(InputTextField(), customField)
        FieldType.INPUT_TEXTAREA -> completeField(InputTextAreaField(), customField)
        FieldType.INPUT_NUMBER -> completeField(InputNumberField(), customField)
        FieldType.INPUT_BOOLEAN -> completeField(InputBooleanField(), customField)
        FieldType.INPUT_DATE -> completeField(InputDateField(), customField)
        FieldType.INPUT_URL -> completeField(InputURLField(), customField)
        FieldType.RADIO -> TODO()
        FieldType.CHECKBOX -> TODO()
        FieldType.LOCATION -> TODO()
        else -> null
    }

    private fun completeField(field: Field, customField: CustomField): Field {
        return field.apply {
            usageKey = customField.usageKey
            id = customField.id
            createdDate = customField.createdDate
            updatedDate = customField.updatedDate
            enabled = customField.enabled
            accessControl = customField.accessControl
            labels = customField.labels?.toStringByLanguage()
            descriptions = customField.descriptions?.toStringByLanguage()
            placeholders = customField.placeholders?.toStringByLanguage()
        }
    }

}