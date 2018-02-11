package io.mysocialapp.customfield.models

/**
 * Created by evoxmusic on 11/02/2018.
 */
object FieldFactory {

    fun from(customField: CustomField): Field? = when (customField.type) {
        FieldType.INPUT_TEXT -> InputTextField(customField)
        FieldType.INPUT_TEXTAREA -> InputTextAreaField(customField)
        FieldType.INPUT_NUMBER -> InputNumberField(customField)
        FieldType.INPUT_BOOLEAN -> InputBooleanField(customField)
        FieldType.INPUT_DATE -> InputDateField(customField)
        FieldType.INPUT_URL -> InputURLField(customField)
        FieldType.RADIO -> TODO()
        FieldType.CHECKBOX -> TODO()
        FieldType.LOCATION -> TODO()
        else -> null
    }

}