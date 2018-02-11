package io.mysocialapp.customfield.models

import io.mysocialapp.customfield.extensions.toLanguage
import io.mysocialapp.customfield.extensions.toStringKeysValues
import io.mysocialapp.customfield.utils.UUID
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
open class InputTextField(private val builder: Builder) : Field {

    override val id: Long = builder.mId
    override val enabled: Boolean = builder.mEnabled
    override val createdDate = builder.mCreatedDate
    override val updatedDate = builder.mUpdatedDate
    override val names = builder.mNames.toMap()
    override val descriptions = builder.mDescriptions.toMap()
    override val placeholders = builder.mPlaceholders.toMap()

    open class Builder(val usageKey: String) {
        var mId: Long = UUID.generateLongId()
        var mEnabled: Boolean = true
        var mCreatedDate: Date = Date()
        var mUpdatedDate: Date = mCreatedDate
        val mNames = mutableMapOf<Language, String>()
        val mDescriptions = mutableMapOf<Language, String>()
        val mPlaceholders = mutableMapOf<Language, String>()

        open fun id(id: Long): Builder {
            this.mId = id
            return this
        }

        open fun enabled(enabled: Boolean): Builder {
            this.mEnabled = enabled
            return this
        }

        open fun createdDate(date: Date): Builder {
            this.mCreatedDate = date
            return this
        }

        open fun updatedDate(date: Date): Builder {
            this.mUpdatedDate = date
            return this
        }

        open fun addName(lang: Language, text: String): Builder {
            mNames[lang] = text
            return this
        }

        open fun addDescription(lang: Language, text: String): Builder {
            mDescriptions[lang] = text
            return this
        }

        open fun addPlaceholder(lang: Language, text: String): Builder {
            mPlaceholders[lang] = text
            return this
        }

        open fun build() = InputTextField(this).apply { checkValidity() }
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

    override fun checkValidity() {
        if (names.isEmpty()) {
            throw MissingMandatoryFieldException("you must add at least one 'name' field")
        }
    }

    override fun validator(customFieldData: CustomFieldData) {

    }

    override val customField: CustomField
        get() = CustomField(
                builder.usageKey,
                builder.mId,
                builder.mCreatedDate,
                builder.mUpdatedDate,
                FieldType.INPUT_TEXT,
                builder.mEnabled,
                builder.mNames.toStringKeysValues(),
                builder.mDescriptions.toStringKeysValues(),
                builder.mPlaceholders.toStringKeysValues(),
                null,
                null,
                null,
                null
        )

}