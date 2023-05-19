package com.jihulab.llh4gitlab.kinoapi.dto.auth

import com.jihulab.llh4gitlab.kinoapi.dto.BaseUpdateDto
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/4/12 11:33
 * @author llh
 */
data class RoleUpdateDto(
//    @get:Schema(title = "角色代号")
//    val code: String,
    @get:Schema(title = "角色名")
    val name: String,
    @get:Schema(title = "权限数据ID")
    var permissionIds: List<Int>? = null,
) : BaseUpdateDto()
