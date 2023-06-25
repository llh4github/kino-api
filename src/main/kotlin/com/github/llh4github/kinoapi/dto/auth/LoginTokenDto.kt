package com.github.llh4github.kinoapi.dto.auth

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.llh4github.kinoapi.contanst.DateTimeConst
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 *
 *
 * Created At 2023/4/2 10:05
 * @author llh
 */
data class LoginTokenDto(
    val username: String,
    val accessToken: String,
    @field:Schema(title = "登录凭证失效时间点")
    @get:JsonFormat(pattern = DateTimeConst.yyyyMMddHHmmss)
    val expires: LocalDateTime,
) {

    @field:Schema(title = "刷新token的凭证")
    val refreshToken = accessToken
}
