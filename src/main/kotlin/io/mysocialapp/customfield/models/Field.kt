package io.mysocialapp.customfield.models

import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
interface Field {

    val id: Long
    val enabled: Boolean
    val createdDate: Date
    val updatedDate: Date
    val names: Map<Language, String>
    val descriptions: Map<Language, String>
    val placeholders: Map<Language, String>

    fun checkValidity()
    fun validator(customFieldData: CustomFieldData)

    val customField: CustomField

}