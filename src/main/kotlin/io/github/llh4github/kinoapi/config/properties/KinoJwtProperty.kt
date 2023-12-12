package io.github.llh4github.kinoapi.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.convert.DurationUnit
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.temporal.ChronoUnit
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 *
 * Created At 2023/12/10 15:19
 * @author llh
 */
@Component
@ConfigurationProperties(prefix = "kino.jwt")
data class KinoJwtProperty(
    /**
     * jwt签发者名称。通常是服务名。
     */
    var issuer: String = "kino-api",

    /**
     * 签名密钥，用于签名 JWT
     */
    var secretKey: String = "BQsHEp0L4F4Klu0ixpuMOlCnMuSzQ",
    /**
     * access jwt过期时间
     */
    @DurationUnit(ChronoUnit.DAYS)
    var accessExpired: Duration = Duration.ofDays(10),

    /**
     * refresh jwt过期时间
     */
    @DurationUnit(ChronoUnit.DAYS)
    var refreshExpired: Duration = Duration.ofDays(30)
) {
    @OptIn(ExperimentalEncodingApi::class)
    val secretBytes by lazy {
        Base64.encode(secretKey.toByteArray()).toByteArray()
    }
}