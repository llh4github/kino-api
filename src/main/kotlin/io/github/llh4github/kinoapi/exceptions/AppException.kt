package io.github.llh4github.kinoapi.exceptions

/**
 * 应用根异常
 * Created At 2023/12/11 22:55
 * @author llh
 */
class AppException(
    code: Int, msg: String, cause: Throwable? = null
) : RuntimeException(msg, cause) {
    constructor(respMsg: RespMsg, cause: Throwable?=null) :
            this(respMsg.code, respMsg.msg, cause)
}