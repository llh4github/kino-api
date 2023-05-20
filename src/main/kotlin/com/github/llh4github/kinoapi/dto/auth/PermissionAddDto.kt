package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.kinoapi.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 *
 *
 * Created At 2023/3/31 14:40
 * @author llh
 */
data class PermissionAddDto(
    @get:NotBlank
    @get:Schema(title = "权限代号")
    val code: String,

    @get:Schema(title = "权限名")
    @get:NotBlank
    val name: String,
) : BaseDto()

/**
 * 权限信息查询参数
 */
typealias PermissionQueryDto = RoleQueryDto
