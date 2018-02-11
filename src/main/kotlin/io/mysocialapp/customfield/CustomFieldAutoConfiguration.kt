package io.mysocialapp.customfield

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.core.CassandraTemplate

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Configuration
class CustomFieldAutoConfiguration {

    @Autowired
    private val cassandraTemplate: CassandraTemplate? = null


}