package com.jihulab.llh4gitlab.kinoapi.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.jihulab.llh4gitlab.kinoapi.contanst.DateTimeConst
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 *
 * Created At 2023/3/28 21:48
 * @author llh
 */
abstract  class BaseDto {
    @get:JsonFormat(pattern = DateTimeConst.yyyyMMddHHmmss)
    @get:Schema(title = "创建时间")
    var createdTime: LocalDateTime? = null

    @get:JsonFormat(pattern = DateTimeConst.yyyyMMddHHmmss)
    @get:Schema(title = "更新时间")
    var updatedTime: LocalDateTime? = null
}