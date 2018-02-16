package io.mysocialapp.customfield.models

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "field_type")
@JsonSubTypes(
        JsonSubTypes.Type(value = InputTextField::class, name = "INPUT_TEXT"),
        JsonSubTypes.Type(value = InputTextAreaField::class, name = "INPUT_TEXTAREA"),
        JsonSubTypes.Type(value = InputNumberField::class, name = "INPUT_NUMBER"),
        JsonSubTypes.Type(value = InputBooleanField::class, name = "INPUT_BOOLEAN"),
        JsonSubTypes.Type(value = InputDateField::class, name = "INPUT_DATE"),
        JsonSubTypes.Type(value = InputURLField::class, name = "INPUT_URL"),
        JsonSubTypes.Type(value = InputLocationField::class, name = "INPUT_LOCATION"),
        JsonSubTypes.Type(value = InputSelectField::class, name = "INPUT_SELECT"),
        JsonSubTypes.Type(value = InputCheckboxField::class, name = "INPUT_CHECKBOX")
)
interface Field {

    var usageKey: String?
    var id: Long?

    val idStr: String?
        get() = id.toString()

    var enabled: Boolean?
    var important: Boolean?
    var accessControl: AccessControl?
    var createdDate: Date?
    var updatedDate: Date?
    var labels: Map<Language, String>?
    var descriptions: Map<Language, String>?
    var placeholders: Map<Language, String>?
    var values: Map<Language, List<String>>?
    var defaultValue: Int?
    var position: Int?

    fun checkValidity()
    fun validator(fieldData: FieldData)

    val customField: CustomField
    val fieldType: FieldType

}