package io.github.llh4github.kinoapi.util

import io.github.llh4github.kinoapi.config.properties.KinoJwtProperty
import io.github.llh4github.kinoapi.constants.JwtType
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.time.LocalDateTime

/**
 *
 * Created At 2023/12/10 15:44
 * @author llh
 */
object JwtUtil {
    val SIG_ALG = Jwts.SIG.HS256
    val SIG_KEY = Jwts.SIG.HS256.key().build()
    val ENCRYPT_ALG = Jwts.ENC.A128CBC_HS256

    enum class FIELDS {
        USER_ID, USERNAME, DATA_SCOPE
    }

    fun createJwt(
        property: KinoJwtProperty,
//        authentication: Authentication
    ): String? {
        val claims = Jwts.claims()
        claims.subject("test")
        claims.add(FIELDS.USER_ID.toString(), "11")
        claims.add(FIELDS.USERNAME.toString(), "Tom")
        claims.add(FIELDS.DATA_SCOPE.toString(), "A")
        return createJwtBuilder(property)
            ?.claims(claims.build())
//            ?.claim("test", "test")
            ?.compact()
    }

    private fun createJwtBuilder(
        property: KinoJwtProperty,
        type: JwtType = JwtType.ACCESS
    ): JwtBuilder? {
        val now = LocalDateTime.now()
        val end = if (type == JwtType.ACCESS) {
            now.plusSeconds(property.accessExpired.seconds)
        } else {
            now.plusSeconds(property.refreshExpired.seconds)
        }
        return Jwts.builder()
            .header()
            .keyId(IdUtil.nextId().toString())
            .and()
            .issuer(property.issuer)
            .issuedAt(DateTimeUtil.toDate(now))
            .expiration(DateTimeUtil.toDate(end))
            .signWith(Keys.hmacShaKeyFor(property.secretBytes))
    }
}