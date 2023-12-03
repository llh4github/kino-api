package io.github.llh4github.kinoapi.config

import org.babyfish.jimmer.sql.meta.DatabaseNamingStrategy
import org.babyfish.jimmer.sql.runtime.DefaultDatabaseNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * Created At 2023/11/30 21:26
 * @author llh
 */
@Configuration
class JimmerConfig {
    @Bean
    fun databaseNamingStrategy(): DatabaseNamingStrategy =
        DefaultDatabaseNamingStrategy.LOWER_CASE
}