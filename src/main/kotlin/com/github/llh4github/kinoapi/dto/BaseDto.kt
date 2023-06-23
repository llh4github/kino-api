package com.github.llh4github.kinoapi.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.llh4github.kinoapi.contanst.DateTimeConst
import com.github.llh4github.kinoapi.model.BaseModelInput
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

/**
 *
 * Created At 2023/3/28 21:48
 * @author llh
 */
abstract class BaseDto  {
    @get:JsonFormat(pattern = DateTimeConst.yyyyMMddHHmmss)
    @get:Schema(title = "创建时间")
    var createdTime: LocalDateTime? = null

    @get:JsonFormat(pattern = DateTimeConst.yyyyMMddHHmmss)
    @get:Schema(title = "更新时间")
    var updatedTime: LocalDateTime? = null

    @JsonIgnore
    @get:Schema(title = "更新者ID", hidden = true)
    var updatedBy: Int? = null

    @JsonIgnore
    @get:Schema(title = "创建者ID", hidden = true)
    var createdBy: Int? = null
}

abstract class BaseUpdateDto : BaseDto() {

    @get:NotNull(message = "必须指定数据ID")
    @get:Min(value = 1, message = "数据ID必须为正整数")
    @get:Schema(title = "数据ID")
    var id: Int = -99
}
