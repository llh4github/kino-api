package io.github.llh4github.kinoapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableConfigurationProperties
class KinoApiApplication

fun main(args: Array<String>) {
    runApplication<KinoApiApplication>(*args)
}
