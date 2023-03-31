package com.jihulab.llh4gitlab.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/3/28 21:48
 * @author llh
 */
data class RoleAddDto(
    @get:Schema(title = "角色代号")
    val code: String,
    @get:Schema(title = "角色名")
    val name: String,
) : BaseDto()

