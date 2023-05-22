package com.github.llh4github.kinoapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * Created At 2023/3/29 18:16
 * @author llh
 */
@Configuration
class SpringdocConfig {
    @Bean
    fun springdocApi(): OpenAPI {
        val api = OpenAPI()
        api.info = Info().title("Kino Web api")
            .version("0.1")
            .description("build with JDK17 + Kotlin")
        return api
    }
}