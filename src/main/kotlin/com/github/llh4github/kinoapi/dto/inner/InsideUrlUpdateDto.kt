package com.github.llh4github.kinoapi.dto.inner

import com.github.llh4github.kinoapi.contanst.HttpMethodEnum
import com.github.llh4github.kinoapi.dto.BaseUpdateDto
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

/**
 *
 *
 * Created At 2023/4/4 16:54
 * @author llh
 */
data class InsideUrlUpdateDto(
    @NotNull
    @get:Schema(title = "请求方法")
    var method: HttpMethodEnum,

    @get:Schema(title = "备注")
    var remark: String = "",

    @get:Schema(title = "权限校验模式")
    val permissionOrMode: Boolean = false,

    @get:Schema(title = "对应权限数据的ID")
    var permissionIds: List<Int> = emptyList(),
) : BaseUpdateDto()
