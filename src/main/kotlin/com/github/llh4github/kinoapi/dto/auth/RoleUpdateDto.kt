package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.jimmerhelper.ToJimmerEntity
import com.github.llh4github.kinoapi.dto.BaseUpdateDto
import com.github.llh4github.kinoapi.model.auth.Role
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/4/12 11:33
 * @author llh
 */
@ToJimmerEntity(Role::class)
data class RoleUpdateDto(
//    @get:Schema(title = "角色代号")
//    val code: String,
    @get:Schema(title = "角色名")
    val name: String,
    @get:Schema(title = "权限数据ID")
    var permissionIds: List<Int>? = null,
) : BaseUpdateDto()
