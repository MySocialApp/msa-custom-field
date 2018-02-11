package io.mysocialapp.customfield.repositories

import io.mysocialapp.customfield.models.CustomField
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Repository
interface CustomFieldRepository : CassandraRepository<CustomField> {

    fun findByUsageKey(usageKey: String): Iterable<CustomField>

}