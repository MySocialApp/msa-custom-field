package io.mysocialapp.customfield.models

import io.mysocialapp.customfield.extensions.toLanguage
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
class InputBooleanField(builder: InputTextField.Builder) : InputTextField(builder) {

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

        override fun addLabel(lang: Language, text: String): Builder {
            super.addLabel(lang, text)
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

        override fun build() = InputBooleanField(this).apply { checkValidity() }
    }

    constructor(cf: CustomField) : this(Builder(cf.usageKey!!).apply {
        id(cf.id!!)
        cf.enabled?.let { mEnabled = it }
        cf.createdDate?.let { createdDate(it) }
        cf.updatedDate?.let { updatedDate(it) }
        cf.labels?.forEach { k, v -> addLabel(k.toLanguage(), v) }
        cf.descriptions?.forEach { k, v -> addDescription(k.toLanguage(), v) }
        cf.placeholders?.forEach { k, v -> addPlaceholder(k.toLanguage(), v) }
    })

    override fun validator(fieldData: FieldData) {
        if (fieldData.value?.toString()?.toLowerCase() != "true" && fieldData.value?.toString()?.toLowerCase() != "false") {
            throw FieldFormatException("field value must be boolean")
        }
    }
}