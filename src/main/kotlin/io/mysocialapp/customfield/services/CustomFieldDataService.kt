package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.models.CustomFieldData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cassandra.core.cql.CqlIdentifier
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Service
class CustomFieldDataService @Autowired constructor(private val cassandraAdminTemplate: CassandraAdminTemplate,
                                                    private val cassandraTemplate: CassandraTemplate) {

    @PostConstruct
    fun init() {
        cassandraAdminTemplate.createTable(true, CqlIdentifier("custom_field_data"), CustomFieldData::class.java, emptyMap())
    }

}