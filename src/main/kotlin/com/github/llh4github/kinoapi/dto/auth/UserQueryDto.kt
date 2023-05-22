package com.github.llh4github.kinoapi.dto.auth

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/3/29 18:46
 * @author llh
 */
data class UserQueryDto(
    @Schema(title = "用户名", example = "Tom")
    val username: String?,
)

