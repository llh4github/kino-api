package io.github.llh4github.kinoapi.exceptions

/**
 *
 * Created At 2023/12/11 23:11
 * @author llh
 */
enum class RespMsgEnums(
    override val code: Int,
    override val msg: String
) : RespMsg {
    OK(1, "OK"),
    ERROR(Int.MAX_VALUE, "未知错误"),
    DATA_NOT_FOUND(404_000, "数据不存在"),
}