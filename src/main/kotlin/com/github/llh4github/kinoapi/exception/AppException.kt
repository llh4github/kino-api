package com.github.llh4github.kinoapi.exception

import com.github.llh4github.kinoapi.contanst.ErrorCode
import com.github.llh4github.kinoapi.contanst.IErrorCode

/**
 *
 * Created At 2023/3/28 21:57
 * @author llh
 */
class AppException : RuntimeException {
    var errorCode: IErrorCode = ErrorCode.UNKNOWN_ERROR

    constructor(message: String? = null) : super(message)
    constructor(code: IErrorCode, message: String? = null) : super(message) {
        errorCode = code
    }

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
