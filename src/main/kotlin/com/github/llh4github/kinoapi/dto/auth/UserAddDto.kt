package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.jimmerhelper.ToJimmerEntity
import com.github.llh4github.kinoapi.dto.BaseDto
import com.github.llh4github.kinoapi.model.auth.User
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

/**
 *
 * Created At 2023/3/29 18:38
 * @author llh
 */
@ToJimmerEntity(User::class)
@Schema(title = "用户添加类")
data class UserAddDto(
    @NotEmpty
    @Schema(title = "用户名", example = "Tom")
    val username: String,
    @Schema(title = "昵称", example = "Tom")
    val nickname: String,
    @Schema(title = "密码", example = "Tom")
    val password: String,

    @Schema(title = "用户拥有角色ID")
    var roleIds: List<Int> = emptyList(),
) : BaseDto()
