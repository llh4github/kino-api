package com.github.llh4github.kinoapi.dto.auth

import com.jihulab.llh4gitlab.kinoapi.dto.BaseUpdateDto
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/5/16 15:57
 * @author llh
 */
data class PermissionUpdateDto(
    @get:Schema(title = "权限名")
    val name: String,
    val code: String,
    @get:Schema(title = "角色数据ID")
    var roleIds: List<Int> = emptyList(),
):BaseUpdateDto()
