package io.github.llh4github.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/12/3 15:33
 * @author llh
 */
@Schema(title = "通用响应体")
data class JsonWrapper<T>(
    @Schema(title = "响应码")
    val code: Int,
    @Schema(title = "响应消息")
    val msg: String,
    @Schema(title = "响应数据")
    val data: T?
) {
    constructor(msg: RespMsg) : this(msg.code, msg.msg, null)
    constructor(msg: RespMsg, data: T?) : this(msg.code, msg.msg, data)
}
