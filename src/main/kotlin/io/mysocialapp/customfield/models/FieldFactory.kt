package io.mysocialapp.customfield.models

/**
 * Created by evoxmusic on 11/02/2018.
 */
object FieldFactory {

    fun from(customField: CustomField): Field? = when(customField.type) {
        FieldType.INPUT_TEXT -> InputTextField(customField)
        FieldType.INPUT_NUMBER -> InputNumberField(customField)
        FieldType.SELECT_BOX -> TODO()
        FieldType.TEXTAREA -> TODO()
        FieldType.CHECKBOX -> TODO()
        FieldType.LOCATION -> TODO()
        FieldType.DATE -> TODO()
        FieldType.BOOLEAN -> TODO()
        else -> null
    }

}