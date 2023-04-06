package com.jihulab.llh4gitlab.kinoapi.exception

import cn.dev33.satoken.exception.NotLoginException
import cn.dev33.satoken.exception.NotPermissionException
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException

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

    @ExceptionHandler(value = [NotLoginException::class])
    fun notLogin(e: NotLoginException): JsonWrapper<Nothing> {
        logger.warn("未登录用户访问接口  ${e.message} ")
        val error = ErrorCode.NOT_LOGIN
        return JsonWrapper(error.code, error.msg, null)
    }

    @ExceptionHandler(value = [NotPermissionException::class])
    fun noPermission(e: NotPermissionException): JsonWrapper<Nothing> {
        logger.warn("用户访问无权限接口  ${e.message} ")
        val error = ErrorCode.NO_PERMISSION
        return JsonWrapper(error.code, error.msg, null)
    }

    @ExceptionHandler(value = [SQLException::class])
    fun pgException(e: SQLException): JsonWrapper<Nothing> {
        logger.error("SQL执行失败", e)
        return JsonWrapper(ErrorCode.DB.code, ErrorCode.DB.msg, null)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validException(e: MethodArgumentNotValidException): JsonWrapper<List<String>> {

        val errorMsg = e.bindingResult.allErrors
            .filterIsInstance<FieldError>()
            .map { Pair(it.field, it.defaultMessage) }
            .groupingBy { it.second }
            .aggregate { _, accumulator: StringBuilder?, element, first ->
                if (first) {
                    StringBuilder().append(element.first)
                } else {
                    accumulator?.append("，")?.append(element.first)
                }
            }
            .filter { it.value != null }
            .map { it.value.toString() + " " + it.key }
            .toList()

        val error = ErrorCode.VALID
        return JsonWrapper(error.code, error.msg, errorMsg)
    }
}