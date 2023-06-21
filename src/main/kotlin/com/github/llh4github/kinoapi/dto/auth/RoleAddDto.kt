package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.jimmerhelper.ToJimmerEntity
import com.github.llh4github.kinoapi.dto.BaseDto
import com.github.llh4github.kinoapi.model.auth.Role
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/3/28 21:48
 * @author llh
 */
@ToJimmerEntity(Role::class)
data class RoleAddDto(
    @get:Schema(title = "角色代号")
    val code: String,
    @get:Schema(title = "角色名")
    val name: String,
    @get:Schema(title = "权限数据ID")
    var permissionIds: List<Int>? = null,
) : BaseDto()

