package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.models.Field
import io.mysocialapp.customfield.models.FieldData
import io.mysocialapp.customfield.models.ResponseFieldData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by evoxmusic on 13/02/2018.
 */
@Service
class ResponseFieldDataService @Autowired constructor(private val customFieldService: CustomFieldService,
                                                      private val customFieldDataService: CustomFieldDataService) {

    fun list(usageKey: String, parentType: String? = null, parentId: Long? = null): List<ResponseFieldData> {
        return customFieldService.list(usageKey).mapNotNull { f -> get(f, parentType, parentId) }
    }

    fun get(usageKey: String, fieldId: Long, parentType: String? = null, parentId: Long? = null): ResponseFieldData? {
        return customFieldService.get(usageKey, fieldId)?.let { get(it, parentType, parentId) }
    }

    fun get(field: Field, parentType: String? = null, parentId: Long? = null): ResponseFieldData? {
        val cf = field.customField
        if (cf.usageKey == null || cf.id == null) {
            return null
        }

        return ResponseFieldData(field, customFieldDataService.get(cf.usageKey, parentType, parentId, cf.id)?.let {
            FieldData(it.customFieldId, it.createdDate, it.updatedDate,
                    it.value ?: it.values ?: mapOf("latitude" to it.latitude, "longitude" to it.longitude))
        })
    }

}