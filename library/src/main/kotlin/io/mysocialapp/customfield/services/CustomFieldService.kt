package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.models.*
import io.mysocialapp.customfield.repositories.CustomFieldRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cassandra.core.cql.CqlIdentifier
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Service
class CustomFieldService @Autowired constructor(private val cassandraAdminTemplate: CassandraAdminTemplate,
                                                private val customFieldRepository: CustomFieldRepository) {


    companion object {
        private const val TABLE = "custom_field"
    }

    @PostConstruct
    fun init() {
        cassandraAdminTemplate.createTable(true, CqlIdentifier(TABLE), CustomField::class.java, emptyMap())
    }

    fun list(): Iterable<Field> = customFieldRepository.findAll().mapNotNull { FieldFactory.from(it) }

    fun list(usageKey: String): Iterable<Field> {
        if (usageKey.isBlank()) {
            return emptyList()
        }

        return customFieldRepository.findByUsageKey(usageKey).mapNotNull { FieldFactory.from(it) }
    }

    fun get(usageKey: String, id: Long): Field? {
        return customFieldRepository.findByUsageKeyAndId(usageKey, id)?.let { FieldFactory.from(it) }
    }

    fun create(vararg field: Field): List<Field> = field.map(::create)

    fun create(field: Field): Field {
        return field.customField.usageKey?.let { create(it, field) } ?: throw MissingMandatoryFieldException("'usage_key' is mandatory")
    }

    fun create(usageKey: String, field: Field): Field {
        customFieldRepository.save(field.customField.copy(usageKey = usageKey))
        return field
    }

    fun update(usageKey: String, id: Long, field: Field): Field {
        val mField = get(usageKey, id) ?: throw DoesNotExistException("Field with usage_key='$usageKey' and id=$id does not exist")

        mField.apply {
            updatedDate = Date()
            mField.enabled = field.enabled
            field.names?.takeIf { it.isNotEmpty() }?.let { mField.names = it }
            field.descriptions?.takeIf { it.isNotEmpty() }?.let { mField.descriptions = it }
            field.placeholders?.takeIf { it.isNotEmpty() }?.let { mField.placeholders = it }
        }

        customFieldRepository.save(mField.customField)

        return field
    }

    fun delete(usageKey: String, id: Long) {
        // TODO do not really delete custom field
        val field = get(usageKey, id) ?: return
        update(usageKey, id, field.apply { enabled = false })
    }

}