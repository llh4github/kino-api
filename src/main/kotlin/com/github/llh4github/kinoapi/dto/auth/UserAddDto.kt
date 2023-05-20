package com.github.llh4github.kinoapi.dto.auth

import com.jihulab.llh4gitlab.kinoapi.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

/**
 *
 * Created At 2023/3/29 18:38
 * @author llh
 */
@Schema(title = "用户添加类")
data class UserAddDto(
    @NotEmpty
    @Schema(title = "用户名", example = "Tom")
    val username: String,
    @Schema(title = "密码", example = "Tom")
    val password: String,

    @Schema(title = "用户拥有角色ID")
    var roleIds: List<Int> = emptyList(),
) : BaseDto()
