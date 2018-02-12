package io.mysocialapp.customfield

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean
import org.springframework.data.cassandra.convert.CassandraConverter
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

/**
 * Created by evoxmusic on 10/02/2018.
 */
@Configuration
@EnableCassandraRepositories(basePackages = ["io.mysocialapp.customfield.repositories"])
class CustomFieldCassandraConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun cassandraAdminTemplate(session: CassandraSessionFactoryBean, converter: CassandraConverter): CassandraAdminTemplate =
            CassandraAdminTemplate(session.`object`, converter)

}