package io.mysocialapp.customfield.models

import io.mysocialapp.customfield.extensions.toLanguage
import io.mysocialapp.customfield.extensions.toStringKeysValues
import io.mysocialapp.customfield.utils.UUID
import java.util.*

/**
 * Created by evoxmusic on 11/02/2018.
 */
open class InputTextField(private val builder: Builder? = null) : Field {

    override val fieldType: FieldType
        get() = FieldType.INPUT_TEXT

    override var id = builder?.mId
    override var enabled = builder?.mEnabled
    override var accessControl = builder?.mAccessControl
    override var createdDate = builder?.mCreatedDate
    override var updatedDate = builder?.mUpdatedDate
    override var names = builder?.mLabels?.toMap()
    override var descriptions = builder?.mDescriptions?.toMap()
    override var placeholders = builder?.mPlaceholders?.toMap()

    open class Builder(val usageKey: String) {
        var mId: Long = UUID.generateLongId()
        var mEnabled: Boolean = true
        var mAccessControl: AccessControl = AccessControl.PRIVATE
        var mCreatedDate: Date = Date()
        var mUpdatedDate: Date = mCreatedDate
        val mLabels = mutableMapOf<Language, String>()
        val mDescriptions = mutableMapOf<Language, String>()
        val mPlaceholders = mutableMapOf<Language, String>()

        open fun fromMap(map: Map<String, Any?>): Builder {
            map["id"]?.let { id(it.toString().toLong()) }
            map["enabled"]?.let { enabled(it.toString().toBoolean()) }
            map["access_control"]?.let { AccessControl.valueOf(it.toString().toUpperCase()) }
            map["created_date"]?.let { createdDate(it as Date) }
            map["updated_date"]?.let { updatedDate(it as Date) }
            map["names"]?.let { (it as Map<String, String>).forEach { k, v -> addLabel(k.toLanguage(), v) } }
            map["descriptions"]?.let { (it as Map<String, String>).forEach { k, v -> addDescription(k.toLanguage(), v) } }
            map["placeholders"]?.let { (it as Map<String, String>).forEach { k, v -> addPlaceholder(k.toLanguage(), v) } }

            return this
        }

        open fun id(id: Long): Builder {
            this.mId = id
            return this
        }

        open fun enabled(enabled: Boolean): Builder {
            this.mEnabled = enabled
            return this
        }

        open fun accessControl(accessControl: AccessControl): Builder {
            this.mAccessControl = accessControl
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

        open fun addLabel(lang: Language, text: String): Builder {
            mLabels[lang] = text
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
        cf.accessControl?.let { mAccessControl = it }
        cf.createdDate?.let { createdDate(it) }
        cf.updatedDate?.let { updatedDate(it) }
        cf.labels?.forEach { k, v -> addLabel(k.toLanguage(), v) }
        cf.descriptions?.forEach { k, v -> addDescription(k.toLanguage(), v) }
        cf.placeholders?.forEach { k, v -> addPlaceholder(k.toLanguage(), v) }
    })

    override fun checkValidity() {
        if (names == null || names?.isEmpty() == true) {
            throw MissingMandatoryFieldException("you must add at least one 'name' field")
        }
    }

    override fun validator(fieldData: FieldData) {
        if (fieldData.value !is String) {
            throw FieldFormatException("field value must be string")
        }
    }

    override val customField: CustomField
        get() = CustomField(
                builder?.usageKey,
                builder?.mId,
                builder?.mCreatedDate,
                builder?.mUpdatedDate,
                fieldType,
                builder?.mEnabled,
                builder?.mAccessControl,
                builder?.mLabels?.toStringKeysValues(),
                builder?.mDescriptions?.toStringKeysValues(),
                builder?.mPlaceholders?.toStringKeysValues(),
                null,
                null,
                null,
                null
        )

}