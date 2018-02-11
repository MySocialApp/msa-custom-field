package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.models.CustomField
import io.mysocialapp.customfield.models.Field
import io.mysocialapp.customfield.models.FieldFactory
import io.mysocialapp.customfield.repositories.CustomFieldRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cassandra.core.cql.CqlIdentifier
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.stereotype.Service
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

    fun list(usageKey: String?): Iterable<Field> {
        if (usageKey == null || usageKey.isBlank()) {
            return emptyList()
        }

        return customFieldRepository.findByUsageKey(usageKey).mapNotNull { FieldFactory.from(it) }
    }

    fun create(field: Field): Field {
        customFieldRepository.save(field.customField)
        return field
    }

}