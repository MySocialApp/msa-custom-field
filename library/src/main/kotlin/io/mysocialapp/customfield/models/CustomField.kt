package io.mysocialapp.customfield.models

import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table
import java.util.*

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Table("custom_field")
data class CustomField(
        @PrimaryKeyColumn(name = "usage_key", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        val usageKey: String? = null,
        @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        val id: Long? = null,
        @Column("created_date")
        val createdDate: Date? = null,
        @Column("updated_date")
        val updatedDate: Date? = null,
        @Column("type")
        val type: FieldType? = null,
        @Column("enabled")
        val enabled: Boolean? = null,
        @Column("labels")
        val labels: Map<String, String>? = null,
        @Column("descriptions")
        val descriptions: Map<String, String>? = null,
        @Column("placeholders")
        val placeholders: Map<String, String>? = null,
        @Column("values")
        val values: List<String>? = null,
        @Column("values_en")
        val valuesEnglish: List<String>? = null,
        @Column("values_fr")
        val valuesFrench: List<String>? = null,
        @Column("default_value")
        val defaultValue: Int? = null)