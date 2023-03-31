package com.jihulab.llh4gitlab.kinoapi.exception

import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 *
 * Created At 2023/3/28 21:57
 * @author llh
 */
@RestControllerAdvice
class AppExceptionHandler : Logging {
    @ExceptionHandler(value = [AppException::class])
    fun appException(e: AppException): JsonWrapper<Nothing> {
        val errorCode = e.errorCode
        val msg = e.message ?: errorCode.msg
        return JsonWrapper(code = errorCode.code, msg, null)
    }
}