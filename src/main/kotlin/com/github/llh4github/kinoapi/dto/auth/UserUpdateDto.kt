package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.kinoapi.dto.BaseUpdateDto
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/5/19 9:02
 * @author llh
 */
@Schema(title = "用户信息修改")
data class UserUpdateDto(

    @Schema(title = "用户拥有角色ID")
    var roleIds: List<Int> = emptyList(),
) : BaseUpdateDto()
