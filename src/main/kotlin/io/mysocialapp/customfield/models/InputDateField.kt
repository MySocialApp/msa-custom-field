package io.mysocialapp.customfield.models

import io.mysocialapp.customfield.extensions.toDate
import io.mysocialapp.customfield.extensions.toLanguage
import java.text.ParseException
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
class InputDateField(builder: InputTextField.Builder) : InputTextField(builder) {

    class Builder(usageKey: String) : InputTextField.Builder(usageKey) {

        override fun fromMap(map: Map<String, Any?>): Builder {
            super.fromMap(map)
            return this
        }

        override fun id(id: Long): Builder {
            super.id(id)
            return this
        }

        override fun enabled(enabled: Boolean): Builder {
            super.enabled(enabled)
            return this
        }

        override fun createdDate(date: Date): Builder {
            super.createdDate(date)
            return this
        }

        override fun updatedDate(date: Date): Builder {
            super.updatedDate(date)
            return this
        }

        override fun addName(lang: Language, text: String): Builder {
            super.addName(lang, text)
            return this
        }

        override fun addDescription(lang: Language, text: String): Builder {
            super.addDescription(lang, text)
            return this
        }

        override fun addPlaceholder(lang: Language, text: String): Builder {
            super.addPlaceholder(lang, text)
            return this
        }

        override fun build() = InputDateField(this).apply { checkValidity() }
    }

    constructor(cf: CustomField) : this(Builder(cf.usageKey!!).apply {
        id(cf.id!!)
        cf.enabled?.let { mEnabled = it }
        cf.createdDate?.let { createdDate(it) }
        cf.updatedDate?.let { updatedDate(it) }
        cf.names?.forEach { k, v -> addName(k.toLanguage(), v) }
        cf.descriptions?.forEach { k, v -> addDescription(k.toLanguage(), v) }
        cf.placeholders?.forEach { k, v -> addPlaceholder(k.toLanguage(), v) }
    })

    override fun validator(fieldData: FieldData) {
        try {
            fieldData.value?.toString()?.toDate()
        } catch (e: ParseException) {
            throw FieldFormatException("field value must be ISO8601 date format")
        }
    }
}