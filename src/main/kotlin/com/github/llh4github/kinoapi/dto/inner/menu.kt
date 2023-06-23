package com.github.llh4github.kinoapi.dto.inner

import com.github.llh4github.jimmerhelper.ToJimmerEntity
import com.github.llh4github.kinoapi.dto.BaseDto
import com.github.llh4github.kinoapi.dto.BaseUpdateDto
import com.github.llh4github.kinoapi.model.inner.MenuFront
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

@ToJimmerEntity(MenuFront::class)
@Schema(title = "添加菜单所需数据")
data class MenuAddDto(

    @field:Length(max = 100, message = "菜单名最多 {max} 个字符")
    @Schema(title = "菜单名")
    val name: String,

    @field:Length(max = 200, message = "前端路径最多 {max} 个字符")
    @Schema(title = "前端路径")
    val router: String,

    @field:Min(value = 1, message = "上级菜单ID必须为非负整数")
    @Schema(title = "上级菜单id")
    val parentId: Int? = null,
) : BaseDto()

@ToJimmerEntity(MenuFront::class)
data class MenuUpdateDto(
    @field:Length(max = 100, message = "菜单名最多 {max} 个字符")
    @Schema(title = "菜单名")
    val name: String,

    @field:Length(max = 200, message = "前端路径最多 {max} 个字符")
    @Schema(title = "前端路径")
    val router: String,

    @field:Min(value = 1, message = "上级菜单ID必须为非负整数")
    @Schema(title = "上级菜单id")
    val parentId: Int? = null,

    ) : BaseUpdateDto()