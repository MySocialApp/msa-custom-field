package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.models.*
import io.mysocialapp.customfield.repositories.CustomFieldDataRepository
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
class CustomFieldDataService @Autowired constructor(private val cassandraAdminTemplate: CassandraAdminTemplate,
                                                    private val customFieldDataRepository: CustomFieldDataRepository,
                                                    private val customFieldService: CustomFieldService) {

    @PostConstruct
    fun init() {
        cassandraAdminTemplate.createTable(true, CqlIdentifier("custom_field_data"), CustomFieldData::class.java, emptyMap())
    }

    fun list(usageKey: String, parentType: String? = null, parentId: Long? = null): List<CustomFieldData> {
        return if (parentType != null && parentId != null) {
            customFieldDataRepository.findByUsageKeyAndParentTypeAndParentId(usageKey, parentType, parentId)
        } else if (parentType != null)
            customFieldDataRepository.findByUsageKeyAndParentType(usageKey, parentType)
        else {
            customFieldDataRepository.findByUsageKey(usageKey)
        }
    }

    fun get(usageKey: String?, parentType: String?, parentId: Long?, fieldId: Long?): CustomFieldData? {
        if (usageKey == null || parentType == null || parentId == null || fieldId == null) {
            return null
        }

        return customFieldDataRepository.findByUsageKeyAndParentTypeAndParentIdAndCustomFieldId(usageKey, parentType, parentId, fieldId)
    }

    fun create(usageKey: String, parentType: String, parentId: Long, vararg fieldData: FieldData): List<ResponseFieldData> = fieldData.map {
        create(usageKey, parentType, parentId, it)
    }

    fun create(usageKey: String, parentType: String, parentId: Long, fieldData: FieldData): ResponseFieldData {
        if (fieldData.fieldId == null) {
            throw MissingMandatoryFieldException("Field 'field_id' is mandatory")
        }

        val field = customFieldService.get(usageKey, fieldData.fieldId)
                ?: throw DoesNotExistException("'field_id' ${fieldData.fieldId} does not exist")

        // check that the field value is valid
        field.validator(fieldData)

        val now = Date()
        val mFieldData = get(usageKey, parentType, parentId, fieldData.fieldId)

        val (value, values, latitude, longitude) = if (fieldData.value is List<*>) {
            arrayOf(fieldData.value.map { it.toString() }, null, null, null)
        } else if (fieldData.value is Map<*, *> && fieldData.value["latitude"] != null && fieldData.value["longitude"] != null) {
            arrayOf(null, null, fieldData.value["latitude"], fieldData.value["longitude"])
        } else {
            arrayOf(null, fieldData.value.toString(), null, null)
        }

        customFieldDataRepository.save(CustomFieldData(usageKey, parentType, parentId, fieldData.fieldId,
                mFieldData?.createdDate ?: now, now, values as List<String>?, value as String?, latitude as Double?, longitude as Double?))

        return ResponseFieldData(field, fieldData.copy(createdDate = now, updatedDate = now))
    }

    fun update(usageKey: String, parentType: String, parentId: Long, fieldData: FieldData): ResponseFieldData {
        return create(usageKey, parentType, parentId, fieldData)
    }

}