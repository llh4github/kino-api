package com.github.llh4github.kinoapi.exception

import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode

/**
 *
 * Created At 2023/3/28 21:57
 * @author llh
 */
class AppException : RuntimeException {
    var errorCode: ErrorCode = ErrorCode.UNKNOWN_ERROR

    constructor(message: String? = null) : super(message)
    constructor(code: ErrorCode, message: String? = null) : super(message) {
        errorCode = code
    }

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
