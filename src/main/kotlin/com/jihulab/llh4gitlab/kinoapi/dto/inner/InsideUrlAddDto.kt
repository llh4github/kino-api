package com.jihulab.llh4gitlab.kinoapi.dto.inner

import com.jihulab.llh4gitlab.kinoapi.contanst.HttpMethodEnum
import com.jihulab.llh4gitlab.kinoapi.dto.BaseDto
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
data class InsideUrlAddDto(
    @NotNull
    @get:Schema(title = "请求方法")
    var method: HttpMethodEnum,

    @field:Pattern(regexp = "^/.*?", message = "必须以/开头")
    @field:NotEmpty
    @get:Schema(title = "url", description = "必须以/开头")
    var url: String,

    @get:Schema(title = "备注")
    var remark: String = "",

    @get:Schema(title = "权限校验模式")
    val permissionOrMode: Boolean = false,

    @get:Schema(title = "对应权限数据的ID")
    var permissionIds: List<Int> = emptyList(),
) : BaseDto()
