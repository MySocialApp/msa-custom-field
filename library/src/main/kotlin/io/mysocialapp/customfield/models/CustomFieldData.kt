package io.mysocialapp.customfield.models

import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table
import java.util.*

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Table("custom_field_data")
data class CustomFieldData(
        @PrimaryKeyColumn(name = "usage_key", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        val usageKey: String? = null,
        @PrimaryKeyColumn(name = "parent_type", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        val parentType: String? = null,
        @PrimaryKeyColumn(name = "parent_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
        val parentId: Long? = null,
        @PrimaryKeyColumn(name = "custom_field_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
        val customFieldId: Long? = null,
        @Column("created_date")
        val createdDate: Date? = null,
        @Column("updated_date")
        val updatedDate: Date? = null,
        @Column("values")
        val values: List<String>? = null,
        @Column("value")
        val value: String? = null,
        @Column("latitude")
        val latitude: Double? = null,
        @Column("longitude")
        val longitude: Double? = null)