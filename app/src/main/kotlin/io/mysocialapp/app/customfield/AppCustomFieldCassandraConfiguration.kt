package io.mysocialapp.app.customfield

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration
import org.springframework.data.cassandra.convert.CassandraConverter
import org.springframework.data.cassandra.core.CassandraAdminTemplate

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Configuration
class AppCustomFieldCassandraConfiguration : AbstractCassandraConfiguration() {

    @Autowired
    private val env: Environment? = null

    @ConditionalOnMissingBean
    override fun getKeyspaceName(): String? =
            env?.getProperty("cassandra.keyspace") ?: env?.getProperty("spring.data.cassandra.keyspace-name")

    @ConditionalOnMissingBean
    override fun getContactPoints(): String? =
            env?.getProperty("cassandra.contact-points") ?: env?.getProperty("spring.data.cassandra.contact-points")

    @ConditionalOnMissingBean
    override fun getPort(): Int =
            env?.getProperty("cassandra.port")?.toInt() ?: env?.getProperty("spring.data.cassandra.port")?.toInt() ?: 9042

    @Bean
    @ConditionalOnMissingBean
    fun cassandraAdminTemplate(converter: CassandraConverter): CassandraAdminTemplate =
            CassandraAdminTemplate(session().`object`, converter)

}