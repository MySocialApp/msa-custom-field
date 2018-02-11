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

    fun list(usageKey: String, parentType: String, parentId: Long): List<ResponseFieldData> {
        val cfs = customFieldDataRepository.findByUsageKeyAndParentTypeAndParentId(usageKey, parentType, parentId)
        return cfs.map { cf ->
            ResponseFieldData(customFieldService.get(usageKey, cf.customFieldId!!),
                    FieldData(cf.customFieldId, cf.createdDate, cf.updatedDate, cf.value ?: cf.values))
        }
    }

    fun get(usageKey: String, parentType: String, parentId: Long, fieldId: Long): ResponseFieldData? {
        val cf = customFieldDataRepository.findByUsageKeyAndParentTypeAndParentIdAndCustomFieldId(usageKey,
                parentType, parentId, fieldId) ?: return null

        return ResponseFieldData(customFieldService.get(usageKey, cf.customFieldId!!),
                FieldData(cf.customFieldId, cf.createdDate, cf.updatedDate, cf.value ?: cf.values))
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

        customFieldDataRepository.save(CustomFieldData(usageKey, parentType, parentId, fieldData.fieldId,
                mFieldData?.data?.createdDate ?: now, now, fieldData.values?.let { listOf(it.toString()) }, fieldData.value?.toString()))

        return ResponseFieldData(field, fieldData.copy(createdDate = now, updatedDate = now))
    }


}