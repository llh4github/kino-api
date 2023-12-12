package io.github.llh4github.kinoapi.config.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

/**
 *
 * Created At 2023/12/10 14:18
 * @author llh
 */
//@Component
class JwtTokenProvider {
    /**
     * 签名密钥，用于签名 Access Token
     */
    @Value("\${jwt.secret-key:M1gGhg5VaFycM6ZdbWs}")
    private lateinit var secretKey: String

    /**
     * jwt过期时间（秒）
     */
    @Value("\${jwt.expiration}")
    private var expiration: Int = 7200

    /**
     * Base64 编码后的签名密钥，用于校验/解析 Access Token
     */
    private val secretKeyBytes by lazy {
        Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(authentication: Authentication): String {
        val claims = Jwts.claims().subject(authentication.name)
        return Jwts.builder()
            .claims(claims.build())
            .issuedAt(Date())
            .expiration(Date())
//            .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
            .compact()
    }
}