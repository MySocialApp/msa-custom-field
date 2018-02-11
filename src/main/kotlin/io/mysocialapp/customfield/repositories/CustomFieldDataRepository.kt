package io.mysocialapp.customfield.repositories

import io.mysocialapp.customfield.models.CustomFieldData
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

/**
 * Created by evoxmusic on 11/02/2018.
 */
@Repository
interface CustomFieldDataRepository : CassandraRepository<CustomFieldData>