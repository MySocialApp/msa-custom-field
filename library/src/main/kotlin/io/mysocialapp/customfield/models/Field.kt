package io.mysocialapp.customfield.models

import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
interface Field {

    val id: Long

    val idStr: String
        get() = id.toString()

    var enabled: Boolean
    var accessControl: AccessControl
    val createdDate: Date
    var updatedDate: Date
    var names: Map<Language, String>
    var descriptions: Map<Language, String>
    var placeholders: Map<Language, String>

    fun checkValidity()
    fun validator(fieldData: FieldData)

    val customField: CustomField

}