package com.github.llh4github.kinoapi.config

import com.github.llh4github.kinoapi.model.ENTITY_MANAGER
import org.babyfish.jimmer.sql.runtime.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * Created At 2023/3/28 20:52
 * @author llh
 */
@Configuration
class JimmerCfg {
    @Bean
    fun entityManager(): EntityManager = ENTITY_MANAGER
}