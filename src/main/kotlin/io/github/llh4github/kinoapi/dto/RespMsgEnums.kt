package io.github.llh4github.kinoapi.dto

/**
 *
 * Created At 2023/12/3 15:36
 * @author llh
 */
enum class RespMsgEnums(
    override val code: Int,
    override val msg: String
) : RespMsg {
    OK(1,"OK"),
}