package com.github.llh4github.kinoapi.dto

import com.github.llh4github.kinoapi.contanst.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/3/28 21:02
 * @author llh
 */
@Schema(title = "通用响应对象（JSON）")
data class JsonWrapper<T>(
    @Schema(title = "响应代号")
    val code: Int = ErrorCode.OK.code,
    @Schema(title = "响应消息")
    val msg: String = ErrorCode.OK.msg,
    @Schema(title = "响应数据体")
    val data: T? = null,
)

