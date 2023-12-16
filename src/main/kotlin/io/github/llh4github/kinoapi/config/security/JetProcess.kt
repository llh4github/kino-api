package io.github.llh4github.kinoapi.config.security

import io.github.llh4github.kinoapi.config.properties.KinoJwtProperty
import io.github.llh4github.kinoapi.constants.JwtType
import io.github.llh4github.kinoapi.dto.RespMsgEnums
import io.github.llh4github.kinoapi.dto.security.UserAuthInfo
import io.github.llh4github.kinoapi.exceptions.AppException
import io.github.llh4github.kinoapi.util.DateTimeUtil
import io.github.llh4github.kinoapi.util.IdUtil
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.crypto.SecretKey

/**
 *
 * Created At 2023/12/10 15:44
 * @author llh
 */
@Component
class JetProcess(val property: KinoJwtProperty) : Logging {
    val signKey: SecretKey by lazy {
        Keys.hmacShaKeyFor(property.secretBytes)
    }

    fun validateJwt(jwt: String): Boolean {
        val parser = Jwts.parser().verifyWith(signKey).build();
        try {
            parser.parseSignedClaims(jwt)
        } catch (e: Exception) {
            logger.error("解析jwt错误，${jwt}", e)
            return false
        }
        return true
    }

    fun parseUserId(jwt: String): Int? {
        return (parseJwt(jwt)?.payload?.get(USER_ID)) as Int?
    }

    fun parseUsername(jwt: String): String? {
        return parseJwt(jwt)?.payload?.get(USERNAME) as String?
    }

    fun parseJwt(jwt: String): Jws<Claims>? {
        val parser = Jwts.parser().verifyWith(signKey).build();
        try {
            return parser.parseSignedClaims(jwt).accept(Jws.CLAIMS)
        } catch (e: Exception) {
            logger.error("解析jwt错误，${jwt}", e)
        }
        return null
    }

    fun createJwt(
        authentication: Authentication,
        type: JwtType
    ): String {
        val claims = Jwts.claims()
        val subject = authentication.principal as String
        val authInfo = authentication.details as UserAuthInfo
        claims.subject(subject)
        claims.add(USER_ID, authInfo.user.userId)
        claims.add(USERNAME, authInfo.username)
        claims.add(TYPE, type.name)
        try {
            return createJwtBuilder(property, type)
                .claims(claims.build())
                .compact()
        } catch (e: Exception) {
            logger.error("创建Jwt出错", e)
            throw AppException(RespMsgEnums.AUTH_ERROR_JWT)
        }
    }

    private fun createJwtBuilder(
        property: KinoJwtProperty,
        type: JwtType = JwtType.ACCESS
    ): JwtBuilder {
        val now = LocalDateTime.now()
        val end = if (type == JwtType.ACCESS) {
            now.plusSeconds(property.accessExpired.seconds)
        } else {
            now.plusSeconds(property.refreshExpired.seconds)
        }
        try {
            return Jwts.builder()
                .header()
                .keyId(IdUtil.nextId().toString())
                .and()
                .issuer(property.issuer)
                .issuedAt(DateTimeUtil.toDate(now))
                .expiration(DateTimeUtil.toDate(end))
                .signWith(signKey)
        } catch (e: Exception) {
            logger.error("创建JwtBuilder出错, $property $type", e)
            throw AppException(RespMsgEnums.AUTH_ERROR_JWT)
        }
    }

    companion object {
        private const val USER_ID = "USER_ID"
        private const val USERNAME = "USERNAME"
        private const val TYPE = "TYP"
    }
}